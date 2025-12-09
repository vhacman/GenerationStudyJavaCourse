package com.generatio.main;

import java.time.LocalDate;

/**
 * Classe che rappresenta un candidato per la posizione di programmatore informatico Back-End
 */
public class Application
{
    
    private String 		name;
    private String 		surname;
    private int	 		birthYear;
    private int 		age;
    private String 		province;
    private String 		degree;
    private String 		subject;
    private int		 	experienceYears;
    private boolean 	availableToTravel;
    private boolean		knowsJava;
    private int 		score;
    private LocalDate 	applicationDate;
    
    // Lazio provinces
    private static final String[] LAZIO_PROVINCES = {
        "ROMA", "LATINA", "FROSINONE", "RIETI", "VITERBO"
    };
    
    /**
     * Costruttore della classe Application
     */
    public Application(String name, String surname, int birthYear, String province, 
                String degree, String subject, int experienceYears, 
                boolean availableToTravel, boolean knowsJava)
    {
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
        this.age = LocalDate.now().getYear() - birthYear;
        this.province = province;
        this.degree = degree;
        this.subject = subject;
        this.experienceYears = experienceYears;
        this.availableToTravel = availableToTravel;
        this.knowsJava = knowsJava;
        this.score = 0;
        this.applicationDate = LocalDate.now();
    }
    
    /**
     * Check if the given province is in Lazio
     */
    public static boolean isProvinceInLazio(String province)
    {
        for (String lazioProvince : LAZIO_PROVINCES) {
            if (province.equals(lazioProvince)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get bonus points based on province
     * Roma e Latina: +20 punti
     * Altre province Lazio: +10 punti
     */
    private int getProvinceBonus(String province) {
        switch (province) {
            case "ROMA":
            case "LATINA":
                return 20;
            case "FROSINONE":
            case "RIETI":
            case "VITERBO":
                return 10;
            default:
                return 0;
        }
    }
    
    /**
     * Get bonus points based on degree subject
     * Informatica o Ingegneria Informatica: +30 punti
     * Matematica o Fisica: +20 punti
     * Statistica o Logica: +10 punti
     */
    private int getDegreeSubjectBonus(String subject)
    {
        if (subject.contains("INFORMATICA") || subject.contains("INGEGNERIA INFORMATICA"))
        {
            return 30;
        } 
        else if (subject.contains("MATEMATICA") || subject.contains("FISICA"))
        {
            return 20;
        } 
        else if (subject.contains("STATISTICA") || subject.contains("LOGICA"))
        {
            return 10;
        }
        return 0;
    }
    
    /**
     * Calcola il punteggio totale della candidatura
     */
    public void calculateScore()
    {
        score = 0;
        score += getProvinceBonus(this.province);
        
        // Punti per titolo di studio
        if (this.degree.equalsIgnoreCase("DIPLOMA"))
        {
            score += 20;
        }
        else if (this.degree.equalsIgnoreCase("LAUREA"))
        {
            score += 30;
            score += getDegreeSubjectBonus(this.subject);
        }
        // Punti per anni di esperienza
        score += (this.experienceYears * 10);
        // Punti per disponibilità a viaggiare
        if (this.availableToTravel)
        {
            score += 20;
        }        
        // Punti per conoscenza di Java
        score += this.knowsJava ? 20 : 10;
    }
    
    /**
     * Genera il messaggio di candidatura formattato
     */
    public String	getApplicationMessage()
    {
        return "=== CANDIDATURA PROGRAMMATORE INFORMATICO BACK-END ===\n\n" +
               "Nome: " + name + "\n" +
               "Cognome: " + surname + "\n" +
               "Età: " + age + " anni (nato/a " + birthYear + ")\n" +
               "Provincia di residenza: " + province + "\n" +
               "Titolo di studio: " + degree + "\n" +
               (!subject.isEmpty() ? "Specializzazione: " + subject + "\n" : "") +
               "Anni di esperienza: " + experienceYears + "\n" +
               "Disponibile a viaggiare: " + (availableToTravel ? "SI" : "NO") + "\n" +
               "Conosce Java: " + (knowsJava ? "SI" : "NO") + "\n" +
               "Data della candidatura: " + applicationDate + "\n" +
               "\n=== PUNTEGGIO FINALE: " + score + " ===\n";
    }
    
    // Getter e Setter
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getSurname()
    {
        return surname;
    }
    
    public void setSurname(String surname)
    {
        this.surname = surname;
    }
    
    public int getBirthYear()
    {
        return birthYear;
    }
    
    public void setBirthYear(int birthYear)
    {
        this.birthYear = birthYear;
        this.age = LocalDate.now().getYear() - birthYear;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public String getProvince()
    {
        return province;
    }
    
    public void setProvince(String province)
    {
        this.province = province;
    }
    
    public String getDegree()
    {
        return degree;
    }
    
    public void setDegree(String degree)
    {
        this.degree = degree;
    }
    
    public String getSubject()
    {
        return subject;
    }
    
    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    
    public int getExperienceYears()
    {
        return experienceYears;
    }
    
    public void setExperienceYears(int experienceYears)
    {
        this.experienceYears = experienceYears;
    }
    
    public boolean isAvailableToTravel()
    {
        return availableToTravel;
    }
    
    public void setAvailableToTravel(boolean availableToTravel)
    {
        this.availableToTravel = availableToTravel;
    }
    
    public boolean isKnowsJava()
    {
        return knowsJava;
    }
    
    public void setKnowsJava(boolean knowsJava)
    {
        this.knowsJava = knowsJava;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public LocalDate getApplicationDate()
    {
        return applicationDate;
    }
    
    public void setApplicationDate(LocalDate applicationDate)
    {
        this.applicationDate = applicationDate;
    }
}