package restassured;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestfulApi {
    private static final String BASE_URL = "https://api.restful-api.dev";

    public static void main(String[] args) {
        getAllProducts();
        // getSingleProduct("1");
        // getObjectById("3");
        // addObject();
        // updateObject("ff808181932badb6019504b6348c28f1");
        // partiallyUpdateObject("ff808181932badb6019504b6348c28f1");
        // deleteObject("ff808181932badb6019504b6348c28f1");
    }

    private static RequestSpecification getRequestSpec() {
        return given()
                .log().all()
                .contentType("application/json");
    }

    public static void getAllProducts() {
        Response response = getRequestSpec()
                .when()
                .get(BASE_URL + "/objects")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Hasilnya Adalah " + response.asPrettyString());
    }

    public static void getObjectById(String id) {
        Response response = getRequestSpec()
                .when()
                .get(BASE_URL + "/objects/" + id)
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Object dengan ID " + id + ": " + response.asPrettyString());
    }

    public static void addObject() {
        String json = "{\n" +
                "   \"name\": \"Apple 13 Pro\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2022,\n" +
                "      \"price\": 1250000,\n" +
                "      \"CPU model\": \"IOS 13\",\n" +
                "      \"Hard disk size\": \"128 GB\"\n" +
                "   }\n" +
                "}";

        Response response = getRequestSpec()
                .body(json)
                .when()
                .post(BASE_URL + "/objects")
                .then()
                .statusCode(201) // Status code untuk sukses POST biasanya 201
                .extract().response();

        System.out.println("Object Berhasil Ditambahkan: " + response.asPrettyString());
    }

    public static void updateObject(String id) {
        String json = "{\n" +
                "   \"name\": \"Apple 13 Pro - Updated\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2023,\n" +
                "      \"price\": 1350000,\n" +
                "      \"CPU model\": \"IOS 14\",\n" +
                "      \"Hard disk size\": \"256 GB\"\n" +
                "   }\n" +
                "}";

        Response response = getRequestSpec()
                .body(json)
                .when()
                .put(BASE_URL + "/objects/" + id)
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Object Berhasil Diperbarui: " + response.asPrettyString());
    }

    public static void partiallyUpdateObject(String id) {
        String json = "{\n" +
                "   \"name\": \"Apple MacBook Pro 16 (Updated Name)\"\n" +
                "}";

        Response response = getRequestSpec()
                .body(json)
                .when()
                .patch(BASE_URL + "/objects/" + id)
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Object Berhasil Diperbarui (Partial): " + response.asPrettyString());
    }

    public static void deleteObject(String id) {
        Response response = getRequestSpec()
                .when()
                .delete(BASE_URL + "/objects/" + id)
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Object Berhasil Dihapus: " + response.asPrettyString());
    }
}
