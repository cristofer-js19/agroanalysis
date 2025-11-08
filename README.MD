# API de Análise Agro-Meteorológica (AgroAnalysis)

Esta é uma API RESTful desenvolvida em Java com Spring Boot. O seu principal objetivo é consolidar dados de índices vegetativos da Embrapa (SATVeg) e previsões meteorológicas (Open-Meteo) para, em seguida, submetê-los a uma análise por um modelo de linguagem (Groq) e retornar insights.

O projeto foi construído seguindo os princípios da **Clean Architecture**, visando garantir separação de responsabilidades, baixo acoplamento e alta testabilidade.

---

## 🚀 Funcionalidades Principais

* **Integração com SATVeg (Embrapa):** Efetua requisições `POST` para o webservice "SATVeg" para coletar perfis temporais de índices vegetativos (NDVI, EVI).
* **Integração com Open-Meteo:** Efetua requisições `GET` para o webservice "Open-Meteo" para obter previsões do tempo (temperatura, precipitação, etc.).
* **Integração com IA Generativa (Groq):** Utiliza um cliente HTTP (`OpenAIOkHttpClient`) para enviar os dados coletados para a API da Groq e gerar análises ou insights.
* **Orquestração de Caso de Uso:** Possui um endpoint principal que orquestra todo o fluxo:
    1.  Coleta dados da SATVeg.
    2.  Coleta dados do Open-Meteo.
    3.  Prepara um prompt com ambos os dados.
    4.  Consulta a API da Groq.
    5.  Retorna a análise gerada.

---

## 🏛️ Arquitetura

A aplicação utiliza **Clean Architecture**, dividindo o código em três camadas principais:

1.  **`domain`:** Contém as entidades de negócio puras (POJOs), sem dependências de frameworks. Ex: `TimeSeriesProfile`, `WeatherForecastProfile`.
2.  **`application`:** Contém a lógica de negócio e os casos de uso (`UseCases`). Define as interfaces (Ports) para comunicação com o mundo exterior.
3.  **`infrastructure`:** Contém os detalhes de implementação e adaptação a tecnologias externas:
    * **`entrypoint`:** Controladores Spring (`@RestController`).
    * **`gateway`:** Implementações dos Ports de saída. É aqui que residem os **Feign Clients**, os DTOs de API (`@JsonProperty`), Mappers e a lógica de cliente para a Groq.
    * **`config`:** Configuração do Spring, Beans, e a configuração dos clientes Feign (incluindo o `RequestInterceptor` customizado para a SATVeg).

---

## 🛠️ Tecnologias Utilizadas

* **Java 21+**
* **Spring Boot**
* **Spring Web** (Controladores REST)
* **Spring Cloud OpenFeign** (Para comunicação REST declarativa com SATVeg e Open-Meteo)
* **Jackson** (Para serialização/deserialização JSON)
* **OpenAI/Groq Client** (via `OpenAIOkHttpClient`)
* **Maven** (Gerenciador de dependências)

---

## ⚙️ Configuração e Execução

Para executar a aplicação localmente, siga os passos abaixo.

### 1. Pré-requisitos

* JDK 21 ou superior
* Maven

### 2. Variáveis de Ambiente

Para que a aplicação se autentique corretamente nas APIs externas, é **obrigatório** configurar as seguintes variáveis de ambiente antes de executar:

* `SATVEG_API_KEY`: A chave de API para autenticação no webservice SATVeg (Embrapa).
* `LLM_API_KEY`: A chave de API para autenticação no webservice da Groq (LLM).

**Exemplo (Linux/macOS):**
```bash
export SATVEG_API_KEY="sua-chave-secreta-satveg"
export LLM_API_KEY="sua-chave-secreta-groq"
```

## 🚀 Exemplo de Uso:

### Request:

```json
{
  "latitude": -13.5302,
  "longitude": -58.88645,
  "username": "Carlos Silva",
  "plant_type": "Lúpulo"
}
```

### Response:

```json
{
  "plant_type": "Lúpulo",
  "weather_analysis": "Com base nos dados do índice vegetativo NDVI e na previsão de precipitação para os próximos 5 dias...",
  "soil_analysis": "Com base nos dados do índice vegetativo NDVI e na previsão de precipitação para os próximos 5 dias...",
}
```