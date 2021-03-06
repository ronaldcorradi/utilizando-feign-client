# Consumindo um serviço utilizando Feign Client
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/ronaldcorradi/utilizando-feign-client/blob/main/LICENSE)

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

### Pacote Entities
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
  
  ### Pacote Repositories
  Ficará a interface responsável pelo acesso ao Banco de Dados. O nome da interface será TrabalhadorRepository
  
  ``` java
  import org.springframework.data.jpa.repository.JpaRepository;

import br.com.feign.trabalhador.entities.Trabalhador;

public interface TrabalhadorRepository extends JpaRepository<Trabalhador,Long>{

}
```

### Pacote Service
Nesse pacote ficará a classe TrabalhadorService. Essa classe que vai ser a facilitadora no acesso dos Entities a partir do framework de persistência e com isso  um Controller passa a enxergar a Service e o repository fica encapsulado nela.

``` java
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.feign.trabalhador.entities.Trabalhador;
import br.com.feign.trabalhador.repositories.TrabalhadorRepository;

@Service
public class TrabalhadorService {

	@Autowired
	private TrabalhadorRepository trabalhadorRepository;
	
	public Optional<Trabalhador> getById(Long id){
		return trabalhadorRepository.findById(id);
	}
	
	public List<Trabalhador> getAll(){
		return trabalhadorRepository.findAll();
	}	
}
```

### Pacote Controller
O controller é uma classe Java com os métodos Http que recebe as requisições e envia as respostas ao cliente.
Nessa aplicação terão apenas dois endpoints, um retornará todos os trabalhadores cadastrados e o outro retornará o resultado da busca pelo id do trabalhador.

``` java
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.feign.trabalhador.entities.Trabalhador;
import br.com.feign.trabalhador.services.TrabalhadorService;


@RestController
@RequestMapping("/trabalhador")
public class TrabalhadorController {
	
	@Autowired
	private TrabalhadorService trabalhadorService;
	
	@GetMapping
	public ResponseEntity<List<Trabalhador>> getAll(){
		return ResponseEntity.ok(trabalhadorService.getAll());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Trabalhador> getById(@PathVariable(name = "id") Long id){
		Optional<Trabalhador> trabalhador = trabalhadorService.getById(id);
		if(trabalhador.isEmpty()){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(trabalhadorService.getById(id).get());
	}

}
```

### Arquivo application.properties do projeto trabalhador
No arquivo application.properties serão informadas as propriedades para utilizadas na aplicação.

A propriedade spring.jpa.defer-datasource-initialization=true garantirá que, após a criação do esquema do Hibernate ser realizada, o adicionalmente schema.sql será lido para quaisquer alterações adicionais do esquema e o data.sql será executado para preencher o banco de dados. 

Os valores das propriedades spring.application.name e server.port serão muito importantes para a aplicação que irá consumir as informações da aplicação trabalhador.

```
#Configuração do Banco de Dados H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

#Habilitar o console do Banco de Dados H2 e seu endereço
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.jpa.defer-datasource-initialization=true

#Número da porta da aplicação
server.port=8001

#Nome da aplicação
spring.application.name=trabalhador
```
### Criação do arquivo data.sql
Clique com o botão direito sobre src/main/resources e selecione new->File.
Nomeie o arquivo para data.sql e insira os comandos abaixo : 
```
INSERT INTO trabalhador (nome,valor_hora) VALUES ('Joaquim',20.00);
INSERT INTO trabalhador (nome,valor_hora) VALUES ('Maria',50.00);
INSERT INTO trabalhador (nome,valor_hora) VALUES ('José',30.00);
INSERT INTO trabalhador (nome,valor_hora) VALUES ('Marcos',25.00);
INSERT INTO trabalhador (nome,valor_hora) VALUES ('Ana',10.00);
```

Ao rodar a aplicação o banco de dados será populado automaticamente com as informações.

# Criando a aplicação de Folha de Pagamento (folha-pagamento)
No Spring Tools Suite (STS) acesse o menu File->New->Spring Starter Project. O nome da aplicação será folha-pagamento.
### As dependências para esse projeto serão as seguintes:
* Spring Boot DevTools
* OpegnFeign
* Spring Web

A estrutura do projeto ficou de acordo com a imagem abaixo

![estrutura-folha](https://user-images.githubusercontent.com/38817390/138925648-029bb6e6-9ad6-4373-bc89-ae08c6464b95.png)

Na classe principal do programa acrescente a anotação @EnableFeignClients.

### Pacote template
No pacote template ficará armazenada a classe Trabalhador. Essa classe terá os mesmos campos da entidade Trabalhador da aplicação trabalhador.

``` java
public class Trabalhador {
	
	private Long id;	
	private String nome;
	private Double valorHoraTrabalhada;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValorHoraTrabalhada() {
		return valorHoraTrabalhada;
	}
	public void setValorHoraTrabalhada(Double valorHoraTrabalhada) {
		this.valorHoraTrabalhada = valorHoraTrabalhada;
	}	
}
```

### Pacote feign
Esse pacote irá conter a interface TrabalhadorFeign que terá a responsabilidade de fazer a requisição na aplicação trabalhador.

```
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.feign.folha.template.Trabalhador;

@Component
@FeignClient(name = "trabalhador",url = "http://localhost:8001", path ="/trabalhador/id/")
public interface WorkerFeign {
	
	@GetMapping("{id}")
	ResponseEntity<Trabalhador> getById(@PathVariable ("id")Long id);

}
```
**@Component** utilizada para a interface ser gerenciada pelo Spring.

**@FeignClient(name = "trabalhador",url = "http://localhost:8001", path ="/trabalhador/id/")**
* name : nome da aplicação que estamos fazendo a requisição. O nome deve ser o mesmo configurado na propriedade spring.application.name do arquivo application.properties da aplicação que será feita a requisição.
* url : endereço da aplicação.
* path : o endpoint que estamos solicitando na requisição.

### Pacote service
O pacote service conterá a classe FolhaPagamentoService.
Essa classe injetará a interface TrabalhadorFeign que fará acesso ao endpoint da aplicação trabalhador.
```
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.feign.folha.feign.TrabalhadorFeign;
import br.com.feign.folha.template.Trabalhador;

@Service
public class FolhaPagamentoService {
	
	@Autowired
	private TrabalhadorFeign trabalhadorFeign;
	
	public Trabalhador getTrabalhadorById(Long id) {
		Trabalhador trabalhador = trabalhadorFeign.getById(id).getBody();
		return trabalhador;
	}

}
```
O método .getBody() vai retornar o corpo da requisição.

### Pacote model
No pacote model teremos a classe FolhaPagamento. Essa classe terá como atributo o trabalhador e o número de dias trabalhados.

```
public class FolhaPagamento {
	
	private Trabalhador trabalhador;
	private int diasTrabalhados;
	
		
	public Trabalhador getTrabalhador() {
		return trabalhador;
	}
	public void setTrabalhador(Trabalhador trabalhador) {
		this.trabalhador = trabalhador;
	}
	public int getDiasTrabalhados() {
		return diasTrabalhados;
	}
	public void setDiasTrabalhados(int diasTrabalhados) {
		this.diasTrabalhados = diasTrabalhados;
	}
	
	public Double getTotal() {
		return this.diasTrabalhados * this.trabalhador.getValorHoraTrabalhada();
	}

}

```
O método getTotal() irá retornar o valor obtido pela multiplicação de dias com o valor da hora do trabalhador.

### Pacote controller
Armazenará a classe FolhaPagamentoController que terá os endpoints da aplicação.

```
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.feign.folha.model.FolhaPagamento;
import br.com.feign.folha.services.FolhaPagamentoService;
import br.com.feign.folha.template.Trabalhador;

@RestController
@RequestMapping("/folha")
public class FolhaPagamentoController {

	@Autowired
	private FolhaPagamentoService folhaPagamentoService;

	@GetMapping("/trabalhador-id/{trabalhador}")
	public ResponseEntity<Trabalhador> getTrabalhador(@PathVariable("trabalhador") Long id) {
		Trabalhador trabalhador = folhaPagamentoService.getTrabalhadorById(id);
		return ResponseEntity.ok(trabalhador);
	}

	@GetMapping("/trabalhador-id/{trabalhador}/dias/{dias}")
	public ResponseEntity<FolhaPagamento> getCalcular(@PathVariable("trabalhador") Long id,
			@PathVariable("dias") int dias) {
		FolhaPagamento folhaPagamento = new FolhaPagamento();
		Trabalhador trabalhador = folhaPagamentoService.getTrabalhadorById(id);
		folhaPagamento.setDiasTrabalhados(dias);
		folhaPagamento.setTrabalhador(trabalhador);
		return ResponseEntity.ok(folhaPagamento);

	}

}
```
No arquivo applicatio.properties da aplicação folha-pagamento a única configuração feita foi a alteração do número da porta para 8002.

**Ao executar os projetos, sempre executar primeiro o projeto responsável por fornecer as informações que serão consumidas.**




[![Github Badge](https://img.shields.io/badge/-Github-000?style=flat-square&logo=Github&logoColor=white&link=https://github.com/ronaldcorradi/)](https://github.com/ronaldcorradi/)
[![Linkedin Badge](https://img.shields.io/badge/-LinkedIn-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/ronald-corradi-costa/)](https://www.linkedin.com/in/ronald-corradi-costa/)
