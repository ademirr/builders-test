1. Importar o projeto em sua IDE como projeto Maven

2. Executar um "maven update" para atualizar dependências

3. Clicar com botão direito na classe BuildersApplication e executar através de "run -> java application"

4. Na ferramenta Postman importar o arquivo "BuildersTest.postman_collection.json" que está na raiz do projeto

5. Na opção "billPayment" da coleção "BuildersTest" clicar no botão "Send" para testar a API que vai receber o número do boleto e a data de pagamento e devolver o valor total com juros e multa, caso tenha. Na aba "body", os campos bar_code e payment_date devem estar preenchidos com valores válidos.

6. É possível executar também pela URL "localhost:8080/swagger-ui/index.html" que também é a documentação das API's do projeto.

7. Para acessar o banco de dados (que é em memória), acessar a URL "http://localhost:8080/h2-console/login.do?jsessionid=58a3829e0977b90703b9b01973b011ad". Utilizar as propriedades que estão no arquivo "src\main\resources\application.properties" para logar no banco.