# User Login System

## Descrição
Abaixo estão listados os erros encontrados no código original, que comprometem sua funcionalidade, segurança e boas práticas de programação.

---

## Erros Encontrados

### 1. **Driver JDBC Incorreto**
- O driver especificado no código está incorreto: `"com.mysql.Driver.Manager"`.
- O driver correto para MySQL é `"com.mysql.cj.jdbc.Driver"`.

---

### 2. **Uso de Credenciais Hardcoded**
- As credenciais do banco de dados estão embutidas no código:
    "String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";"
    Essa prática é insegura e pode expor informações sensíveis caso o código seja compartilhado.

---

### 3. **Manipulação Silenciosa de Exceções**
- As exceções são capturadas sem tratamento ou registro:
    "} catch (Exception e) { }"
    Essa abordagem dificulta a identificação de erros e viola boas práticas de depuração.

---

### 4. **Vulnerabilidade a SQL Injection**
- A construção de consultas SQL é feita por meio de concatenação de strings:
  sql += "select nome from usuarios ";
  sql += "where login = '" + login + "'";
  sql += " and senha = '" + senha + "';";

---

### 5. **Falta de Fechamento de Recursos**
- Conexões (`Connection`), `Statement` e `ResultSet` não são fechados após o uso.
- Isso pode causar **vazamento de recursos**, especialmente em aplicações com uso intensivo de banco de dados.

---

### 6. **Ausência de Verificação de Conexão**
- O código não verifica se a conexão foi estabelecida antes de executar consultas.
- Se conn for null, um NullPointerException pode ocorrer.

---

### 7. **Construção de SQL Ineficiente**
- A construção de SQL diretamente no código pode ser difícil de manter em sistemas maiores.
- O uso de parâmetros de consulta (via PreparedStatement) é mais eficiente e seguro.