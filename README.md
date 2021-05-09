# Spring-Boot-Microservices-and-Spring-cloud
Spring boot, Netflix  Eureka, Spring Cloud Api Gateway , Spring Cloud Config ,RabbitMq ve Spring security kullanarak microservice mimarisinde web servisleri oluşturdum.

Projede aşşagıdaki spring projeleri yer almaktadır.
->PhotoAppApiGatewayService
->PhotoAppDiscoveryService
->PhotoAppConfigServer
->PhotoAppAccountWs
->PhotoAppAlbumWs
->PhotoAppUserWs

PhotoAppDiscoveryService:
  Client-side service discovery projesi.Projede Netflix Eureka hizmet kaydı oluşturacak ve ardından hem kendisini kayıt belelgine kaydettiren hem de kendi ana bilgisayarını çözmek için kullanan bir istemci oluşturdum Hizmet kayıt defteri, istemci tarafında yük dengelemeyi etkinleştirdiği ve servis sağlayıcıları DNS'ye ihtiyaç duymadan tüketicilerden ayırdığı için kullanışlıdır.
  BKNZ: https://spring.io/guides/gs/service-registration-and-discovery/
        https://www.baeldung.com/spring-cloud-netflix-eureka

PhotoAppApiGatewayService:
  Spring Cloud Api Gateway projesi. Spring Cloud Gateway, API'lere yönlendirme için basit ama etkili bir yol sağlamayı ve bunlara güvenlik, izleme / ölçümler ve esneklik gibi       kesişen endişeler sağlamayı amaçlamaktadır.
  BKNZ:  https://spring.io/projects/spring-cloud-gateway
         https://www.baeldung.com/spring-cloud-gateway


PhotoAppConfigServer:
  Spring cloud config projesidir.Projedeki config dosyalarındaki degişikliklerin yönetimi icin oluşturdum. Spring cloud config dağıtılmış bir sistemde haricileştirilmiş yapılandırma için sunucu tarafı ve istemci tarafı desteği sağlar. Config Server ile, tüm ortamlardaki uygulamalar için harici özellikleri yönetmek için merkezi bir  web service dir. Config dosyaların depolanması icin git yapılandırmasını kullandım ve Verilerin runtime zamanında güncellenmesi icin RabbitMq kullandım.
  
 BKNZ: https://cloud.spring.io/spring-cloud-config/reference/html/
       https://www.baeldung.com/spring-cloud-configuration
      
PhotoAppUserWs:
  Spring boot projesi.Projede rest api lerin oldugu kullanıcı işlemlerinin ve login register ve token alma , yetkilendirme  gibi işlemlerin yapıldıgı web service.
 
PhotoAppAccountWs:
  Spring boot projesi.Projede rest api lerin oldugu kullanıcıların hesap işlemlerininin yapıldıgı web service.
 
PhotoAppAlbumWs:
  Spring boot projesi.Projede rest api lerin oldugu kullanıcılarının fotografla ilgili  işlemlerininin yapıldıgı web service.

