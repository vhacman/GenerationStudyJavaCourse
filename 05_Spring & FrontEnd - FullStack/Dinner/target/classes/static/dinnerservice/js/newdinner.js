let controller = {
    insertAPIURL: '/dinnerservice/api/jr/dinners',

    menuPrices: {
        'Pizza Margherita': 12, 'Pizza Quattro Formaggi': 15, 'Pizza Diavola': 14,
        'Pizza Capricciosa': 16, 'Pizza Prosciutto e Funghi': 14, 'Pizza Marinara': 10,
        'Pizza Calzone': 13, 'Pizza Quattro Stagioni': 16, 'Pizza Hawai': 15,
        'Pizza Vegetariana': 13, 'Lasagna della casa': 18, 'Lasagna Bolognese': 19,
        'Lasagna Vegetariana': 17, 'Spaghetti alla Carbonara': 14, 'Spaghetti all Amatriciana': 13,
        'Spaghetti al Pesto': 12, 'Spaghetti alle Vongole': 16, 'Penne all Arrabbiata': 12,
        'Penne al Pomodoro': 11, 'Rigatoni alla Norma': 13, 'Risotto ai Funghi Porcini': 18,
        'Risotto alla Seafood': 20, 'Risotto al Pomodoro': 15, 'Risotto alla Zafferano': 19,
        'Tagliatelle al Ragu': 16, 'Tagliatelle al Pesto': 15, 'Gnocchi al Pomodoro': 13,
        'Gnocchi ai Funghi': 15, 'Tortellini in Brodo': 14, 'Tortellini al Pesto': 15,
        'Pollo alla Griglia': 16, 'Pollo alla Parmigiana': 18, 'Pollo Fritto': 14,
        'Bistecca alla Griglia': 22, 'Bistecca alla Fiorentina': 25, 'Arrosto di Manzo': 24,
        'Hamburger Classico': 12, 'Hamburger Deluxe': 15, 'Salmone alla Griglia': 22,
        'Salmone in Crosta': 24, 'Pesce Spada alla Griglia': 20, 'Frittura di Mare': 18,
        'Calamari Fritti': 14, 'Gamberoni alla Griglia': 21, 'Insalata Caesar': 11,
        'Insalata Greca': 10, 'Insalata Caprese': 12, 'Insalata Waldorf': 13,
        'Minestrone della casa': 9, 'Zuppa di Pomodoro': 8
    },

    init: function() {
        const datalist = document.getElementById('dinner-suggestions');
        for (let piatto in this.menuPrices) {
            let option = document.createElement('option');
            option.value = piatto;
            datalist.appendChild(option);
        }
    },

    updatePrice: function() {
        const dishName = form1.description.value;
        form1.cost.value = this.menuPrices[dishName] || "";
        this.checkForm();
    },

    checkForm: function() {
        let btn = document.getElementById('savebtn');
        // Abilita solo se descrizione e costo sono compilati
        let isValid = (form1.description.value !== '' && form1.cost.value !== '');
        btn.disabled = !isValid;
    },

    prepareAndSend: function() {
        // Cambia lo stato a delivered prima dell'invio
        form1.status.value = "delivered";
        this.callAPI();
    },

    callAPI: function() {
        let json = {
            description: form1.description.value,
            cost: form1.cost.valueAsNumber,
            status: form1.status.value
        };

        fetch(this.insertAPIURL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(json)
        })
        .then(response => response.json())
        .then(data => {
            alert('Ordine registrato con successo!');
            location.reload();
        })
        .catch(err => alert('Errore nell\'invio dell\'ordine.'));
    }
};

window.onload = () => controller.init();