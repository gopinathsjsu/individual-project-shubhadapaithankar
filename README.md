# individual-project-shubhadapaithankar
individual-project-shubhadapaithankar created by GitHub Classroom


## Steps to run the program

### Step 1: Compile the project using maven

In order to compile the project, run the following command in the project directory containing pom.xml.

```
mvn clean install
```

It will generate the jar file. 


### Step 2: Run the jar file

Run the jar file with using following command.
```
java -cp  target/individual-project-shubhadapaithankar-1.0-SNAPSHOT.jar  com.cmpe202.Billing inventory.csv input.csv config.properties

```

### Step 3: Check the output file generate if the program ran successfully.

For a SUCCESSFUL transaction, `successful_order_output.csv` is generated in the same directory.
For a UNSUCCESSFUL transaction, `unsuccessful_order_output.txt` is generated in the same directory.



Here are the arguments you need to give. There should be three arguments for the jar to run.  

* Argument 1: Path to the inventory.csv (this is your inventory csv file)
* Argument 2: Path to the input.csv (this is your input order csv file)
* Argument 3: Path to the config.properties (this is where you configure the cap of each category)


## How to configure cap on each category

We can configure cap on each cateogory by modifying `config.properties` file. 

Here is the cap configured by default. 

```
Essentials=3
Luxury=4
Misc=3
```

## Sample Runs (Test cases)

### A Successful transaction

We have sample input files. Here is how you can run a successful transaction by using `input1.csv`

input1.csv

```
Item,Quantity,CardNumber
Shampoo,2,4.12E+12
chocolates,1
Wallet,1
Pen,1
```

Run the following command 

```aidl
java -cp  target/individual-project-shubhadapaithankar-1.0-SNAPSHOT.jar  com.cmpe202.Billing inventory.csv input1.csv config.properties

```

Generated output file will be `successful_order_output.csv`

Here is the content of the output file.

```aidl

Item, Quantity, Price, TotalPrice
Pen,1,3.0,126.0
Shampoo,2,20.0
chocolates,1,3.0
Wallet,1,100.0

```

### A Unsuccessful transaction (By Quantity)

We have sample input files. Here is how you can run an un-successful transaction by using `input2.csv`

input2.csv

```
Item,Quantity,CardNumber
Shampoo,201,4.12E+12
chocolates,1
Wallet,1
Pen,1

```

Here is config.properties (We changed the limit to the category Essentials)

```aidl
Essentials=300
Luxury=4
Misc=3
```

Run the following command

```aidl
java -cp  target/individual-project-shubhadapaithankar-1.0-SNAPSHOT.jar  com.cmpe202.Billing inventory.csv input2.csv config.properties

```

Generated output file will be `unsuccessful_order_output.txt`

Here is the content of the output file.

```aidl

Please Correct Quantities : Shampoo, 

```


### A Unsuccessful transaction (By Cap)

We have sample input files. Here is how you can run an un-successful transaction by using `input3.csv`

input2.csv

```
Item,Quantity,CardNumber
Shampoo,201,4.12E+12
chocolates,1
Wallet,1
Pen,1

```

Here is config.properties (We will revert the limit to the category Essentials)

```aidl
Essentials=3
Luxury=4
Misc=3
```

Run the following command

```aidl
java -cp  target/individual-project-shubhadapaithankar-1.0-SNAPSHOT.jar  com.cmpe202.Billing inventory.csv input3.csv config.properties

```

Generated output file will be `unsuccessful_order_output.txt`

Here is the content of the output file.

```aidl
Max Cap Exceeded For Essentials: Cap: 3, Count of Essentials in the Order: 201

```

### Class Diagram

Here is the class diagram: 




### Design Patterns: 

# Singleton 

### Participants: 

CardDatabase

# Chain of Responsibility

### Participants: 

Handler Interface: Handler
ConcreteHandlers: CardReaderHandler, CapValidationHandler, OrderSuccessfulHandler, QuantityValidationHandler


# Factory

### Participants: 

Factory

# Composite 

### Participants:

Componet Inteface,
Composite: Inventory
Leaf: Essentials, Luxury, Misc

