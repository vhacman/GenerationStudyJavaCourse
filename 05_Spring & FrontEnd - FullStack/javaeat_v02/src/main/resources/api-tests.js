class Test
{
    constructor(name, uri, verb, status, verify, body)
    {
        this.name = name;
        this.uri = uri;
        this.body = body;
        this.verb = verb;
        this.status = status;
        this.verify = verify;
    }
    
    run()
    {
       const options = {
            method: this.verb.toUpperCase(),
            headers: {
                'Content-Type': 'application/json'
            }
        };

        // Il body viene incluso solo se il verbo non Ã¨ GET
        if (this.verb.toUpperCase() !== 'GET' && this.body) {
            options.body = JSON.stringify(this.body);
        }

        fetch(this.uri, options)
        .then(response => {
            console.log(this.status);
            if(response.status!=this.status)
                throw new Error("Wrong status, expected  "+this.status+" got "+response.status+" for test "+this.name);
            return response.json();
        })
        .then(json=>{


            if(this.verify)
            {
                let res = this.verify(json);
                if(!res)
                    throw new Error("Verification failed for test "+this.name+" verication function "+this.verify+", json "+json);
            }
            console.log(this);
            console.log("passed for");
            console.log(json);
            console.log("=================================");
        })
        .catch(error => {
            console.error(error);
            throw error;
        }); 
    }    
}


let tests = [

    // ==================== CITY ====================
    new Test("City - Find All",             "http://localhost:8080/javaeat/api/cities",                "GET",  200, json=>json.length>0),
    new Test("City - Find By Id",           "http://localhost:8080/javaeat/api/cities/1",              "GET",  200, json=>json.id==1),
    new Test("City - Find By Id Not Found", "http://localhost:8080/javaeat/api/cities/999999",         "GET",  404, null),
    new Test("City - Find By Province",     "http://localhost:8080/javaeat/api/cities/province/TO",    "GET",  200, json=>json.province=='TO'),
    new Test("City - Find By Name",         "http://localhost:8080/javaeat/api/cities/search/Torino",  "GET",  200, json=>json.name=='Torino'),
    new Test("City - Insert",              "http://localhost:8080/javaeat/api/cities",                "POST", 200, json=>json.id>0,                {name:"TestCity",province:"TC"}),
    new Test("City - Insert No Name",      "http://localhost:8080/javaeat/api/cities",                "POST", 400, null,                           {province:"XX"}),
    new Test("City - Insert No Province",  "http://localhost:8080/javaeat/api/cities",                "POST", 400, null,                           {name:"Test"}),

    // ==================== RESTAURANT ====================
    new Test("Restaurant - Find All",             "http://localhost:8080/javaeat/api/restaurants",              "GET",  200, json=>json.length>=0),
    new Test("Restaurant - Find By Id",           "http://localhost:8080/javaeat/api/restaurants/1",            "GET",  200, json=>json.id==1),
    new Test("Restaurant - Find By Id Not Found", "http://localhost:8080/javaeat/api/restaurants/999999",       "GET",  404, null),
    new Test("Restaurant - Find By City",         "http://localhost:8080/javaeat/api/restaurants/city/1",       "GET",  200, json=>json.length>0),
    new Test("Restaurant - Find By Name",         "http://localhost:8080/javaeat/api/restaurants/search/Pizzeria",  "GET",  200, json=>json.length>0),
    new Test("Restaurant - Insert",              "http://localhost:8080/javaeat/api/restaurants",              "POST", 200, json=>json.id>0,
        {name:"TestRestaurant",email:"test@rest.com",pw:"pass123",address:"Via Test 1",capacity:50,city:{id:1}}),
    new Test("Restaurant - Insert No Name",      "http://localhost:8080/javaeat/api/restaurants",              "POST", 400, null,
        {email:"x@x.com",pw:"123",address:"Via 1",capacity:10,city:{id:1}}),
    new Test("Restaurant - Insert No Email",     "http://localhost:8080/javaeat/api/restaurants",              "POST", 400, null,
        {name:"Test",pw:"123",address:"Via 1",capacity:10,city:{id:1}}),
    new Test("Restaurant - Insert No City",      "http://localhost:8080/javaeat/api/restaurants",              "POST", 400, null,
        {name:"Test",email:"x@x.com",pw:"123",address:"Via 1",capacity:10}),
    new Test("Restaurant - Insert Neg Capacity", "http://localhost:8080/javaeat/api/restaurants",              "POST", 400, null,
        {name:"Test",email:"x@x.com",pw:"123",address:"Via 1",capacity:-5,city:{id:1}}),

    // ==================== COSTUMER ====================
    new Test("Costumer - Find All",             "http://localhost:8080/javaeat/api/costumers",              "GET",  200, json=>json.length>=0),
    new Test("Costumer - Find By Id",           "http://localhost:8080/javaeat/api/costumers/1",            "GET",  200, json=>json.id==1),
    new Test("Costumer - Find By Id Not Found", "http://localhost:8080/javaeat/api/costumers/999999",       "GET",  404, null),
    new Test("Costumer - Find By City",         "http://localhost:8080/javaeat/api/costumers/city/1",       "GET",  200, json=>json.length>0),
    new Test("Costumer - Find By LegalName",    "http://localhost:8080/javaeat/api/costumers/search/a",     "GET",  200, json=>json.length>0),
    new Test("Costumer - Insert",              "http://localhost:8080/javaeat/api/costumers",              "POST", 200, json=>json.id>0,
        {email:"testcost@test.com",pw:"pass123",legalName:"TestCostumer",address:"Via Cliente 1",city:{id:1}}),
    new Test("Costumer - Insert No Email",     "http://localhost:8080/javaeat/api/costumers",              "POST", 400, null,
        {pw:"123",legalName:"Test",address:"Via 1",city:{id:1}}),
    new Test("Costumer - Insert No LegalName", "http://localhost:8080/javaeat/api/costumers",              "POST", 400, null,
        {email:"x@x.com",pw:"123",address:"Via 1",city:{id:1}}),

    // ==================== DISH ====================
    new Test("Dish - Find All",             "http://localhost:8080/javaeat/api/dishes",                "GET",  200, json=>json.length>=0),
    new Test("Dish - Find By Id",           "http://localhost:8080/javaeat/api/dishes/1",              "GET",  200, json=>json.id==1),
    new Test("Dish - Find By Id Not Found", "http://localhost:8080/javaeat/api/dishes/999999",         "GET",  404, null),
    new Test("Dish - Find By Restaurant",   "http://localhost:8080/javaeat/api/dishes/restaurant/1",   "GET",  200, json=>json.length>0),
    new Test("Dish - Find By Name",         "http://localhost:8080/javaeat/api/dishes/search/a",       "GET",  200, json=>json.length>0),
    new Test("Dish - Insert",              "http://localhost:8080/javaeat/api/dishes",                "POST", 200, json=>json.id>0,
        {name:"TestPiatto",price:8,description:"Piatto di test",restaurantId:1}),
    new Test("Dish - Insert No Name",      "http://localhost:8080/javaeat/api/dishes",                "POST", 400, null,
        {price:10,description:"Test",restaurantId:1}),
    new Test("Dish - Insert Neg Price",    "http://localhost:8080/javaeat/api/dishes",                "POST", 400, null,
        {name:"Test",price:-5,description:"Test",restaurantId:1}),

    // ==================== RIDER ====================
    new Test("Rider - Find All",             "http://localhost:8080/javaeat/api/riders",                  "GET",  200, json=>json.length>=0),
    new Test("Rider - Find By Id",           "http://localhost:8080/javaeat/api/riders/1",                "GET",  200, json=>json.id==1),
    new Test("Rider - Find By Id Not Found", "http://localhost:8080/javaeat/api/riders/999999",           "GET",  404, null),
    new Test("Rider - Find By Status",       "http://localhost:8080/javaeat/api/riders/status/AVAILABLE", "GET",  200, json=>json.length>0),
    new Test("Rider - Find By LegalName",    "http://localhost:8080/javaeat/api/riders/search/a",         "GET",  200, json=>json.length>0),
    new Test("Rider - Insert",              "http://localhost:8080/javaeat/api/riders",                  "POST", 200, json=>json.id>0,
        {email:"testrider@test.com",pw:"pass123",legalName:"TestRider",serviceCost:5,status:"AVAILABLE"}),
    new Test("Rider - Insert No Email",     "http://localhost:8080/javaeat/api/riders",                  "POST", 400, null,
        {pw:"123",legalName:"Test",serviceCost:5,status:"AVAILABLE"}),
    new Test("Rider - Insert No LegalName", "http://localhost:8080/javaeat/api/riders",                  "POST", 400, null,
        {email:"x@x.com",pw:"123",serviceCost:5,status:"AVAILABLE"}),
    new Test("Rider - Insert Neg Cost",     "http://localhost:8080/javaeat/api/riders",                  "POST", 400, null,
        {email:"x@x.com",pw:"123",legalName:"Test",serviceCost:-3,status:"AVAILABLE"}),

    // ==================== DELIVERY ====================
    new Test("Delivery - Find All",                   "http://localhost:8080/javaeat/api/deliveries",                          "GET",  200, json=>json.length>=0),
    new Test("Delivery - Find By Id",                 "http://localhost:8080/javaeat/api/deliveries/1",                        "GET",  200, json=>json.id==1),
    new Test("Delivery - Find By Id Not Found",       "http://localhost:8080/javaeat/api/deliveries/999999",                   "GET",  404, null),
    new Test("Delivery - Find By Restaurant",         "http://localhost:8080/javaeat/api/deliveries/restaurant/1",             "GET",  200, json=>json.length>0),
    new Test("Delivery - Find By Costumer",           "http://localhost:8080/javaeat/api/deliveries/costumer/1",               "GET",  200, json=>json.length>0),
    new Test("Delivery - Find By Rider",              "http://localhost:8080/javaeat/api/deliveries/rider/1",                  "GET",  200, json=>json.length>0),
    new Test("Delivery - Find By Costumer+Status",    "http://localhost:8080/javaeat/api/deliveries/costumer/1/status/OPEN",   "GET",  200, json=>json.length>0),
    new Test("Delivery - Find By Restaurant+Status",  "http://localhost:8080/javaeat/api/deliveries/restaurant/1/status/OPEN", "GET",  200, json=>json.length>0),
    new Test("Delivery - Find By Rider+Status",       "http://localhost:8080/javaeat/api/deliveries/rider/1/status/OPEN",      "GET",  200, json=>json.length>0),
    new Test("Delivery - Count By Costumer",          "http://localhost:8080/javaeat/api/deliveries/count/costumer/1",         "GET",  200, json=>json>=0),
    new Test("Delivery - Count By Restaurant",        "http://localhost:8080/javaeat/api/deliveries/count/restaurant/1",       "GET",  200, json=>json>=0),
    new Test("Delivery - Count By Rider",             "http://localhost:8080/javaeat/api/deliveries/count/rider/1",            "GET",  200, json=>json>=0),
    new Test("Delivery - Count By Status",            "http://localhost:8080/javaeat/api/deliveries/count/status/OPEN",        "GET",  200, json=>json>=0),
    new Test("Delivery - Insert",                    "http://localhost:8080/javaeat/api/deliveries",                          "POST", 200, json=>json.id>0,
        {description:"Test consegna",status:"OPEN",price:25.50,day:"2025-01-15T12:30:00",restaurantId:1,costumerId:1,riderId:1}),
    new Test("Delivery - Insert No Status",          "http://localhost:8080/javaeat/api/deliveries",                          "POST", 400, null,
        {price:10,restaurantId:1,costumerId:1,riderId:1}),
    new Test("Delivery - Insert Neg Price",          "http://localhost:8080/javaeat/api/deliveries",                          "POST", 400, null,
        {status:"OPEN",price:-10,restaurantId:1,costumerId:1,riderId:1}),
];

for(let t of tests)
    t.run();