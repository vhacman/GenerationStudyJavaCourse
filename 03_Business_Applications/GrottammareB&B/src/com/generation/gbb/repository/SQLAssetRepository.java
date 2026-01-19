package com.generation.gbb.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.generation.gbb.cache.TotalCache;
import com.generation.gbb.model.database.ConnectionFactory;
import com.generation.gbb.model.entities.Asset;
import com.generation.gbb.profiling.ProfilingMonitor;
import com.generation.gbb.repository.interfaces.AssetRepository;

public class SQLAssetRepository implements AssetRepository
{
    Connection connection = ConnectionFactory.make();
    TotalCache<Asset> cache = new TotalCache<Asset>();

    /**
     * Costruttore: inizializza il repository.
     * La cache viene popolata lazy (alla prima chiamata a findAll())
     * per permettere il reset del ProfilingMonitor prima del caricamento.
     */
    public SQLAssetRepository() {}

    /**
     * Verifica se la tabella asset esiste nel database.
     */
    private void checkTable()
    {
        try
        {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet rs = meta.getTables(null, null, "asset", null);

            if (!rs.next())
                throw new RuntimeException("Tabella 'asset' non trovata. Eseguire initTable()");

            rs.close();
        }
        catch (SQLException e)
        {
        	e.printStackTrace();
            throw new RuntimeException("Errore verifica tabella asset");
        }
    }

    /**
     * Recupera tutti gli asset dal database.
     * Alla prima chiamata esegue la query, poi restituisce dalla cache.
     *
     * @return lista contenente tutti gli asset presenti nel database/cache
     */
    @Override
    public List<Asset> findAll()
    {
        checkTable();
        // Se la cache è già popolata, restituisci direttamente dalla cache (0 query)
        if(cache.getContent() != null && !cache.getContent().isEmpty())
            return cache.getContent();
        // Prima chiamata: carica dal database usando findWhere con condizione sempre vera
        List<Asset>		result = findWhere("1=1");
        // Popola la cache per le chiamate successive
        cache.setContent(result);
        return result;
    }

    /**
     * Cerca un asset specifico tramite identificativo.
     * Utilizza la TotalCache per accesso immediato (0 query).
     * Se la cache non è popolata, la popola automaticamente chiamando findAll().
     *
     * @param id l'identificativo univoco dell'asset
     * @return l'oggetto Asset se trovato, null altrimenti
     */
    @Override
    public Asset		findById(int id)
    {
        checkTable();        
        // Assicura che la cache sia popolata
        if(cache.getContent() == null || cache.getContent().isEmpty())
            findAll(); // Popola la cache        
        return cache.findById(id);
    }

    /**
     * Cerca asset per nome.
     * Usa findWhere per query personalizzata.
     *
     * @param name il nome da cercare
     * @return lista di asset con il nome specificato
     */
    public List<Asset>		findByName(String name)
    {
        return findWhere("name = '" + name + "'");
    }

    /**
     * Cerca asset con costo inferiore o uguale a un valore specificato.
     * Usa findWhere per query personalizzata.
     *
     * @param maxCost il costo massimo
     * @return lista di asset con costo <= maxCost
     */
    public List<Asset>		findByCostLessThan(int maxCost)
    {
        return findWhere("cost <= " + maxCost);
    }

    /**
     * Cerca asset con valore superiore o uguale a un valore specificato.
     * Usa findWhere per query personalizzata.
     *
     * @param minValue il valore minimo
     * @return lista di asset con value >= minValue
     */
    public List<Asset>		findByValueGreaterThan(int minValue)
    {
        return findWhere("value >= " + minValue);
    }

    /**
     * Metodo generico PRIVATO per recuperare asset con una condizione WHERE personalizzata.
     * Esegue una query SQL sul database applicando la condizione specificata.
     *
     * @param condition la condizione SQL da applicare (senza la parola chiave WHERE)
     * @return lista di asset che soddisfano la condizione
     */
    private List<Asset>			findWhere(String condition)
    {
        checkTable();

        try
        {
            String sql = "SELECT * FROM asset WHERE " + condition;
            PreparedStatement readCmd = connection.prepareStatement(sql);
            List<Asset> result = new ArrayList<>();
            ResultSet assetRows = readCmd.executeQuery();
            ProfilingMonitor.queryNumber++;
            while(assetRows.next())
            {
                result.add(rowToAsset(assetRows));
                ProfilingMonitor.rowsLoaded++;
            }
            readCmd.close();
            assetRows.close();
            return result;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Error reading assets with condition: " + condition);
        }
    }

    /**
     * Mappa una riga del ResultSet in un oggetto Asset.
     *
     * @param assetRow il ResultSet posizionato sulla riga corrente
     * @return un oggetto Asset popolato con i dati della riga
     * @throws SQLException se si verifica un errore durante la lettura
     */
    private Asset rowToAsset(ResultSet assetRow) throws SQLException
    {
        Asset result = new Asset();

        result.setId(assetRow.getInt("id"));
        result.setName(assetRow.getString("name"));
        result.setDescription(assetRow.getString("description"));
        result.setCost(assetRow.getInt("cost"));
        result.setValue(assetRow.getInt("value"));

        return result;
    }

    /**
     * Inserisce un nuovo asset nel database.
     * Aggiorna immediatamente la cache per mantenere la sincronizzazione.
     *
     * @param newAsset l'oggetto Asset da persistere
     * @return l'oggetto Asset con l'id generato dal database
     * @throws RuntimeException se l'asset non è valido o ha già un id
     */
    @Override
    public Asset insert(Asset newAsset)
    {
        checkTable();

        if (!newAsset.isValid())
            throw new RuntimeException("Invalid asset: " + newAsset.getErrors());

        if (newAsset.getId() != 0)
            throw new RuntimeException("Cannot save an asset with a previous id");

        try
        {
            String	sql = "INSERT INTO asset (name, description, cost, value) VALUES (?,?,?,?)";
            PreparedStatement insertCmd = connection.prepareStatement(sql);

            insertCmd.setString(1, newAsset.getName());
            insertCmd.setString(2, newAsset.getDescription());
            insertCmd.setInt(3, newAsset.getCost());
            insertCmd.setInt(4, newAsset.getValue());
            insertCmd.execute();

            ProfilingMonitor.queryNumber++;
            insertCmd.close();

            newAsset.setId(getNewId());

            // Aggiungi alla cache per sincronizzazione (se la cache è popolata)
            if(cache.getContent() != null)
                cache.getContent().add(newAsset);

            return newAsset;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Error saving asset");
        }
    }

    /**
     * Recupera l'ultimo ID generato dal database.
     * Usa findWhere per ottenere l'ID massimo.
     *
     * @return l'ID massimo presente nella tabella asset
     */
    private int getNewId()
    {
        try
        {
            PreparedStatement 	readCmd = connection.prepareStatement("SELECT MAX(id) AS m FROM asset");
            ResultSet 			rs 		= readCmd.executeQuery();

            ProfilingMonitor.queryNumber++;

            rs.next();
            int res = rs.getInt("m");
            readCmd.close();
            rs.close();

            return res;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Error reading max id");
        }
    }

    /**
     * Aggiorna i dati di un asset esistente.
     * Sincronizza la cache rimuovendo la vecchia versione e aggiungendo la nuova.
     *
     * @param newVersion l'oggetto Asset con i dati aggiornati
     * @return l'oggetto Asset aggiornato
     */
    @Override
    public Asset update(Asset newVersion)
    {
        checkTable();

        if (!newVersion.isValid())
            throw new IllegalArgumentException("Invalid asset data: " + newVersion.getErrors());

        if (newVersion.getId() <= 0)
            throw new IllegalArgumentException("Cannot update an asset without a valid id");

        Asset oldVersion = findById(newVersion.getId());

        if (oldVersion == null)
            throw new IllegalArgumentException("Asset con ID " + newVersion.getId() + " non trovato");

        try
        {
            String sql = "UPDATE asset SET name=?, description=?, cost=?, value=? WHERE id=?";
            PreparedStatement updateCmd = connection.prepareStatement(sql);

            updateCmd.setString(1, newVersion.getName());
            updateCmd.setString(2, newVersion.getDescription());
            updateCmd.setInt(3, newVersion.getCost());
            updateCmd.setInt(4, newVersion.getValue());
            updateCmd.setInt(5, newVersion.getId());
            updateCmd.execute();

            ProfilingMonitor.queryNumber++;

            // Sincronizza la cache (rimuovi vecchia versione, aggiungi nuova)
            if(cache.getContent() != null)
            {
                cache.getContent().remove(oldVersion);
                cache.getContent().add(newVersion);
            }

            updateCmd.close();

            return newVersion;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Error updating asset");
        }
    }

    /**
     * Elimina un asset dal database.
     * Rimuove immediatamente l'asset dalla cache per mantenere la sincronizzazione.
     *
     * @param id l'identificativo dell'asset da eliminare
     * @return true se l'eliminazione ha successo, false altrimenti
     */
    @Override
    public boolean delete(int id)
    {
        checkTable();

        Asset toDelete = findById(id);

        if (toDelete == null)
            throw new IllegalArgumentException("Asset con ID " + id + " non trovato");

        try
        {
            String sql = "DELETE FROM asset WHERE id = ?";
            PreparedStatement deleteCmd = connection.prepareStatement(sql);

            deleteCmd.setInt(1, id);
            deleteCmd.execute();

            ProfilingMonitor.queryNumber++;

            // Rimuovi dalla cache per sincronizzazione
            if(cache.getContent() != null)
                cache.getContent().remove(toDelete);

            deleteCmd.close();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Crea la tabella asset nel database se non esiste già.
     */
    @Override
    public void initTable()
    {
        try
        {
            String 				createTableSQL = "CREATE TABLE IF NOT EXISTS asset ("
									                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
									                + "name VARCHAR(100) NOT NULL, "
									                + "description TEXT, "
									                + "cost INTEGER NOT NULL, "
									                + "value INTEGER NOT NULL)";
            PreparedStatement 	statement = connection.prepareStatement(createTableSQL);

            statement.executeUpdate();

            ProfilingMonitor.queryNumber++;
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Errore nella creazione della tabella asset");
        }
    }
}
