# KI 3

### Aufgabe 1 - Modellierung von GA

#### a) Travelling Salesman

##### Kodierung

0123456789  

0573928164  

Die Indizes der Städte in der Reihenfolge, in der sie besucht werden.



##### Crossover





##### Mutationen

0573928164  -> 0573928 | 164  

057 | 164 | 3928 -> 0571643928

Zufällig Sub-Pfade an andere Stellen verschieben



##### Fitnessfunktion

Gesamte Distanz des Pfades



#### b) Sudoku

##### Kodierung

123456789123456789...

Blockweise Notation, in lese-flow



##### Crossover

Blöcke, Reihen oder Spalten von Individuen tauschen



##### Mutationen

Zufällige Zahl zu zufälligem Wert ändern



##### Fitnessfunktion

Anzahl der Überlappungen im Block / Reihe / Spalte



#### c) n-Queens

##### Kodierung

01234567

57362401

Index = Spalte  

Wert = Zeile



##### Crossover

Individuen zerschneiden und austauschen



##### Mutationen

Zufällige Zahl zufällig ändern



##### Fitnessfunktion

Anzahl der Überlappungen in Reihe / Spalte



### Aufgabe 1 - Simulated Annealing

Simulated Annealing benötigt einen Startzustand, Übergänge zu weiteren Zuständen und eine Fitnessfunktion zur Bewertung gegen das Optimum. Es fehlen also: Übergänge.



Ich habe mir das so vorgestellt: Übergänge sind eine festgelegte Mutation, z.B. das Tauschen von zwei Städten im gleichen Pfad beim Travelling Salesman Problem. So hat man eine begrenzte Zahl an "benachbarten Zuständen", aus denen man dann mit Hilfe der Fitnessfunktion die beste, oder mit einer gewissen Chance eine schlechtere Möglichkeit auswählt. Die Chance für die Auswahl einer schlechteren Möglichkeit geht nach jedem Schritt etwas runter.



### Aufgabe 2 - Implementierung I

##### Testergebnis #1 - Basis

Werte:

```java
int n = 8;
int maxGenerations = 10000;
int individualCount = 100;
int tournamentSize = 2;
float mutationOdds = 0.001f;
float crossOdds = 0.6f;
int repeatAlgorithm = 100;
```

Ergebnis:

```
Average count of generations until solution was found: 6310.52
Average count of generations until at or below 1 fitness was found: 679.17206
Average count of generations until at or below 2 fitness was found: 8.7
```



##### Testergebnis #2 - Höhere Chance auf Mutation

```java
float mutationOdds = 0.01f;
```

Ergebnis:

```
Average count of generations until solution was found: 2331.65
Average count of generations until at or below 1 fitness was found: 36.61
Average count of generations until at or below 2 fitness was found: 4.08
```



##### Testergebnis #3 - Höhere Chance auf Crossover

```java
float crossOdds = 0.95f;
```

Ergebnis:

```
Average count of generations until solution was found: 6840.26
Average count of generations until at or below 1 fitness was found: 532.4792
Average count of generations until at or below 2 fitness was found: 7.18
```



##### Testergebnis #4 - Größere Turniere I

```java
float tournamentSize = 10;
```

Ergebnis:

```
Average count of generations until solution was found: 5600.77
Average count of generations until at or below 1 fitness was found: 588.6064
Average count of generations until at or below 2 fitness was found: 9.1
```



##### Testergebnis #5 - Größere Turniere II

```java
float tournamentSize = 30;
```

Ergebnis:

```
Average count of generations until solution was found: 6365.75
Average count of generations until at or below 1 fitness was found: 462.011
Average count of generations until at or below 2 fitness was found: 16.24
```



##### Testergebnis #6 - Noch höhere Chance auf Mutation

```java
float mutationOdds = 0.1f;
int repeatAlgorithm = 1000;
```

Ergebnis:

```
Average count of generations until solution was found: 677.447
Average count of generations until at or below 1 fitness was found: 31.367495
Average count of generations until at or below 2 fitness was found: 4.092277
```



##### Testergebnis #7 - NOCH höhere Chance auf Mutation

```java
float mutationOdds = 0.3f;
int repeatAlgorithm = 1000;
```

Ergebnis:

```
Average count of generations until solution was found: 2137.647
Average count of generations until at or below 1 fitness was found: 75.02264
Average count of generations until at or below 2 fitness was found: 5.4018035
```


##### Testergebnis #8 - Etwas geringere Chance auf Mutation

```java
float mutationOdds = 0.05f;
int repeatAlgorithm = 1000;
```

Ergebnis:

```
Average count of generations until solution was found: 173.124
Average count of generations until at or below 1 fitness was found: 18.410149
Average count of generations until at or below 2 fitness was found: 3.7336683
```





### Aufgabe 3 - Implementierung II

#### Kodierung: Individuum

Pfad durch alle Punkte als Liste von (x,y) Koordinaten



#### Kodierung: Operatoren

Point Mutation:

- Tausch von zwei Punkten innerhalb des Pfades eines Individuums

Shuffle Mutation:

- Sub-Pfad zufälliger Länge an eine andere Position des Pfades verschieben



#### Fitnessfunktion

Gesamte Distanz auf dem Pfad





### Aufgabe 4 - Anwendungen

Beispiel EA: Automotive Design

- Materialien und Formen zur Erreichung der optimalen Aerodynamik

