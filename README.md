# Utilizando Feign Client

Neste exemplo serão construídas duas aplicações para fins didáticos mostrando como consumir um serviço utilizando o Feign Client do Spring Cloud.

### Tecnologias utilizadas

* Java 11
* Spring Jpa
* Spring Web
* Banco de dados em memória H2
* Open Feign do Spring Cloud


A primeira aplicação construída será a aplicação Trabalhador. Essa aplicação irá fornecer as informaçãoes que serão consumidas por outra aplicação, a aplicação Folha de Pagamento.

No Spring Tools Suite (STS) acesse o menu File->New->Spring Starter Project. O nome da aplicação como dito acima, será trabalhador.
As dependências utilizadas nessa aplicação serão :

* H2 Database
* Spring Data JPA
* Spring Web
* Spring Boot Devtools

A estrutura do projeto ficou conforme a imagem abaixo:

![estrutura-trabalhador](https://user-images.githubusercontent.com/38817390/138903449-78e6f123-a73c-4aff-8b19-5fa50a1f86fa.png)

## Pacote Entities
Nesse pacote ficará a entidade(Entity) Trabalhador.

``` java
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trabalhador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String nome;	
	@Column(name = "valor_hora")
	private Double valorHoraTrabalhada;
	
	public Trabalhador() {
	}
	
	public Trabalhador(String nome, Double valorHoraTrabalhada) {
		this.nome = nome;
		this.valorHoraTrabalhada = valorHoraTrabalhada;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Double getValorHoraTrabalhada() {
		return valorHoraTrabalhada;
	}	
  ```
  
  ## Pacote Repositories
  Ficará a interface responsável pelo acesso ao Banco de Dados. O nome da interface será TrabalhadorRepository
  
  ``` java
  import org.springframework.data.jpa.repository.JpaRepository;

import br.com.feign.trabalhador.entities.Trabalhador;

public interface TrabalhadorRepository extends JpaRepository<Trabalhador,Long>{

}
```





[![Github Badge](https://img.shields.io/badge/-Github-000?style=flat-square&logo=Github&logoColor=white&link=https://github.com/ronaldcorradi/)](https://github.com/ronaldcorradi/)
[![Linkedin Badge](https://img.shields.io/badge/-LinkedIn-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/ronald-corradi-costa/)](https://www.linkedin.com/in/ronald-corradi-costa/)
