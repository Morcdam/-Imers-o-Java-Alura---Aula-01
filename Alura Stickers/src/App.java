import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // BUSCAR OS DADOS
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // EXTRAIR OS DADOS
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // EXIBIR OS DADOS
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println(String.format("\u001B[37m\u001B[4m\u001B[1m%s\u001B[0m: \u001B[34m%s", "Filme", filme.get("title")));
            System.out.println(String.format("\u001B[37m\u001B[4m\u001B[1m%s\u001B[0m: \u001B[34m%s", "Data de lançamento", filme.get("year")));
            System.out.println(String.format("\u001B[37m\u001B[4m\u001B[1m%s\u001B[0m: \u001B[34m%s\u001B[33m%s \u2B50", "Avaliação do IMDb", "\u001B[33m", filme.get("imDbRating")));
            System.out.println();
        } 
    }
}