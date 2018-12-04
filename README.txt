================================================================================================== Installation Instruction =========================
git clone https://github.com/makelepe/shoppingbasket.git to your local workstation (assuming to shoppingbasket folder)
cd to shoppingbasket folder and run:
	- mvn clean install
and 
	- mvn spring-boot:run 
	
This should run the service

Alternatively:
copy target/shoppingbasket-0.0.1-SNAPSHOT.jar to your prefarred location and run as below

java -jar target/shoppingbasket-0.0.1-SNAPSHOT.jar





================================================================================================== User Instruction =========================
GET REQUEST
---------------------------------------------------------------------------
http://localhost:8080/shopping/basket/username


POST/DELETE JSON
--------------------------------------------------------------------------
http://localhost:8080/shopping/basket/username
{
	"quantity" : 1, 
	"name": "wine",
	"price": 12.2
}


IN-Memory DB URL
---------------------------------------------------------------------------
http://localhost:8080/h2-console


Run 
---------------------------------------------------------------------------
mvn spring-boot:run



