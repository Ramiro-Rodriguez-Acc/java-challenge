En la V2 se buscara cachear los datos en una BD redis

```mermaid
classDiagram
class PointOfSale {
    +int id
    +String name
    +List<Cost> costs
    }
class Cost {
    +int id
    +Long cost
    +PointOfSale pointOfSaleFrom
    +PointOfSale pointOfSaleto
    }
class Accreditation {
    +int id
    +int amount
    +LocalDate creationDate
    +int pointOfSaleId
    }

    PointOfSale "1:N" --> Cost : 
    PointOfSale --> Accreditation : 
    Cost "2:1" --> PointOfSale :  
```
# Deployar proyecto:


cmd:
```
build_and_run
```
A ese script se pueden mandar como entrada # java-challenge o gateway si 
desa saltear la construccion de uno de los dos. Si usa docker en lugar 
de podman puede mandar docker como entrada tambien. El comando con todas
estas entradas seria(son todas opcionales):
```
build_and_run gateway java-challenge docker
```
