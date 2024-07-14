# DOCKER KULANIMI

## IMAGE OLUSTURMA
     docker build --build-arg JAR_FILE=<build path > -t  <image name> .
     docker build --build-arg JAR_FILE=build/libs/MovieApp_Hs1-0.0.1-SNAPSHOT.jar -t hs1movieapp:v001 .
     build path: servisimizden build aldığımız zaman oluşan jar dosyasının konumu
    image name: oluşturacağımız image e vereceğimiz isim ( versiyon numarası vermeyi unutmayın !!! )
##  IMAGE UZERİNDEN CONTAINER CALISTIRMA
    docker run -d -p dısport:içport hs1movieapp:v001
    docker run -d -p 9096:8090 hs1movieapp:v001
    içport: application yml da uygulamanın ayağa kalktığı port
    dışport: containerın dışarıya açıldığı port istekler bu porta gelecek bu port iç porta yonlendirecek
    docker run --name java9postgres -e POSTGRES_PASSWORD=root -d -p 5442:5432 postgres (dockerhub uzerinden 
     run komutuyla beraber postgres image dosyası cekilip bu komutla beraber çalışmaktadır burda databse e baglanma
      şifresini  - e olarak bir environment olarak tanımladık)
## NETWORK OLUSTURMA
    docker network ls: var olan networklerimizi listeleme
    docker network rm somenetwork : network adıyla networkumuzu silme
    docker network create --driver bridge --subnet <ag portları > --gateway 182.18.0.1 < network name>
    1- docker network create --driver bridge --subnet 182.18.0.1/24 --gateway 182.18.0.1 hs1movienetwork
    2-docker network create --driver bridge --subnet 192.168.3.1/24  --gateway 192.168.3.1 hs1movienetwork
    iki kodda aynı işlevi yapmaktadır burda farklı subnetler verilebileceğini gostermek için tekrar başka 
    subnet ler ile aynı kodu yazdım                                                     
    ag portları: networkumuzdeki ip aralığını belirlediğmiz yer
    network create komutu ile bir network olusturabiliriz
### NETWORKE CONTAINER BAGLAMA
    docker run --name hs1postgres -e POSTGRES_PASSWORD=root --net hs1movienetwork -d -p 5442:5432 postgres
    java9postgres adında bir postgresl container'ı olusturduk 
    --net java9-network komutu ile olusturdugumuz hs1movienetwork 'une  postgresqlimizi bağladık
    daha sonra apllication yml da db_url imizi değiştirdik
    url: jdbc:postgresql://localhost:5442/movidb yerine artık 
    jdbc:postgresql://hs1postgres/moviedb yazdık burda 
    localhost yerine aslında olusturdugumuz postgresql containernın ismini verdik 
    ve pg adminden register ile 5442 daki postgeslimize bağlandık ve java9Movie_App_Db adında bir databse olusturduk
    daha sonra uygulamamızı tekrar build edip uygulamamızdan bir image olusturduk 
     docker build --build-arg JAR_FILE=build/libs/MovieApp_Hs1-0.0.1-SNAPSHOT.jar -t hs1movieapp:v002 .
    ve bu image'i çalıstırıken olusturduğumuz java9-networkune asagıdaki kodla bağladık
    docker run --net  hs1movienetwork -d -p 9090:8090 hs1movieapp:v002 
