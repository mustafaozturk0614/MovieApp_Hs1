# DOCKER KULANIMI

## IMAGE OLUSTURMA
     docker build --build-arg JAR_FILE=<build path > -t  <image name> .
     docker build --build-arg JAR_FILE=build/libs/MovieApp_Hs1-0.0.1-SNAPSHOT.jar -t hs1movieapp:v001 .
     build path: servisimizden build aldığımız zaman oluşan jar dosyasının konumu
    image name: oluşturacağımız image e vereceğimiz isim ( versiyon numarası vermeyi unutmayın !!! )
## IMAGE UZERİNDEN CONTAINER CALISTIRMA
    docker run -d -p dısport:içport hs1movieapp:v001
    docker run -d -p 9096:8090 hs1movieapp:v001
    içport: application yml da uygulamanın ayağa kalktığı port
    dışport: containerın dışarıya açıldığı port istekler bu porta gelecek bu port iç porta yonlendirecek
    docker run --name java9postgres -e POSTGRES_PASSWORD=root -d -p 5442:5432 postgres (dockerhub uzerinden 
     run komutuyla beraber postgres image dosyası cekilip bu komutla beraber çalışmaktadır burda databse e baglanma
      şifresini  - e olarak bir environment olarak tanımladık)
