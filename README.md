Projekt prezentujący techniki sprzątania kodu.

Prezentowana aplikacja jest backendem programu służącego do wynajdywania rowerów na zamówienie. Użytkownik może 
zdefiniować jakie parametry w rowerze go interesują a aplikacja przejrzy katalog rowerów różnych producentów (w tym 
przypadku wybrane rowery marek [Romet](https://romet.pl/) oraz [Riese & Müller](https://www.r-m.de/en-us/)) i zwróci użytkownikowi modele 
pasujące do zapytania

Na branchu `main` znajduje się wersja kodu przed posprzątaniem, bez testów jednostkowych oraz zawierająca błędy.

Natomiast na branchu `poprawione` znajduje się kod z poprawionymi błędami wraz z testami jednostkowymi

Celem zadania jest poprawa kodu znajdującego się w pliku `FindBike.java`, napisanie testów jednostowych oraz naprawa 
błędu opisanego poniżej:

### POLSL-12345 NullPointerException przy podaniu maksymalnej wagi roweru

#### Przy wykonywaniu zapytania do serwisu zdarza się wystąpienie wyjątku NullPointerException. Błąd występuje tylko kiedy zdefiniowana jest maksymalna masa roweru

Wewnątrz pakietu `dataProviders` znajdują się RESTowe kontrolery mające na celu symulowanie innych serwisów 
internetowych, na potrzeby testowania. Kod ten należy uznać za black box.

W pakiecie `model` znajdują się DTO oraz enumy. Wprowadzanie zmian w nich nie jest konieczne, ale są one częścią 
kodu dostępnego użytkownikowi (można więc w nich wprowadzać zmiany, w ramach uznania)

Zadanie dla chętnych: dodanie dodatkowych parametrów (np. zakresu wagi rowerów - marka R&M oferuje warianty rowerów
których masa może się różnić nawet o kilka kilogramów)

Powodzenia!