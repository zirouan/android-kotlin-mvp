package br.com.liveo.mvp.data.local.endpoint

/**
 * Created by rudsonlima on 10/20/17.
 */

object EndPointJson {

    //region Methods Users
    internal val USER_JSON_SUCCESS = "{\n" +
            "\t\"page\": 2,\n" +
            "\t\"per_page\": 3,\n" +
            "\t\"total\": 12,\n" +
            "\t\"total_pages\": 4,\n" +
            "\t\"data\": [{\n" +
            "\t\t\"id\": 4,\n" +
            "\t\t\"first_name\": \"Eve\",\n" +
            "\t\t\"last_name\": \"Holt\",\n" +
            "\t\t\"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/marcoramires/128.jpg\"\n" +
            "\t}, {\n" +
            "\t\t\"id\": 5,\n" +
            "\t\t\"first_name\": \"Charles\",\n" +
            "\t\t\"last_name\": \"Morris\",\n" +
            "\t\t\"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/stephenmoon/128.jpg\"\n" +
            "\t}, {\n" +
            "\t\t\"id\": 6,\n" +
            "\t\t\"first_name\": \"Tracey\",\n" +
            "\t\t\"last_name\": \"Ramos\",\n" +
            "\t\t\"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/bigmancho/128.jpg\"\n" +
            "\t}]\n" +
            "}"

    internal val USER_JSON_FAIL = "{\n" +
            "\t\"errorMessage\": \"No records.\"\n" +
            "}"
    //endregion
}
