<?php

$phpVersion = "1.1";
$response = array();
$jsonObjPar = file_get_contents('php://input');

if (! empty($jsonObjPar)) {

    $jsonObj = json_decode($jsonObjPar, true);

    if (! empty($jsonObj)) {

        if (isset($jsonObj["version"])) {

            if ($jsonObj["version"] == $phpVersion) {

                if (isset($jsonObj["type"])) {

                    switch ($jsonObj["type"]) {
                        case 1:
                            include 'query/sp_q1.php';
                            break;
                        case 2:
                            include 'query/sp_q2.php';
                            break;
                        case 3:
                            include 'query/sp_q3.php';
                            break;
                        case 4:
                            include 'query/sp_q4.php';
                            break;
                        case 5:
                            include 'query/sp_q5.php';
                            break;
                        case 6:
                            include 'query/sp_q6.php';
                            break;
                        case 7:
                            include 'query/sp_q7.php';
                            break;
                        case 8:
                            include 'query/sp_q8.php';
                            break;
                        case 9:
                            include 'query/sp_q9.php';
                            break;
                        case 10:
                            include 'query/sp_q10.php';
                            break;
                        case 11:
                            include 'query/sp_q11.php';
                            break;
                        case 12:
                            include 'query/sp_q12.php';
                            break;
                        case 13:
                            include 'query/sp_q13.php';
                            break;
                        case 14:
                            include 'query/sp_q14.php';
                            break;
                        case 15:
                            include 'query/sp_q15.php';
                            break;
                        case 16:
                            include 'query/sp_q16.php';
                            break;
                        case 17:
                            include 'query/sp_q17.php';
                            break;
                        case 18:
                            include 'query/sp_q18.php';
                            break;
                        case 19:
                            include 'query/sp_q19.php';
                            break;
                        case 20:
                            include 'query/sp_q20.php';
                            break;
                        case 21:
                            include 'query/sp_q21.php';
                            break;
                        case 22:
                            include 'query/sp_q22.php';
                            break;
                        case 23:
                            include 'query/sp_q23.php';
                            break;
                        case 24:
                            include 'query/sp_q24.php';
                            break;
                        case 25:
                            include 'query/sp_q25.php';
                            break;
                        case 26:
                            include 'query/sp_q26.php';
                            break;
                        case 27:
                            include 'query/sp_q27.php';
                            break;
                        case 28:
                            include 'query/sp_q28.php';
                            break;
                        case 29:
                            include 'query/sp_q29.php';
                            break;
                        case 30:
                            include 'query/sp_q30.php';
                            break;
                        case 31:
                            include 'query/sp_q31.php';
                            break;
                        case 32:
                            include 'query/sp_q32.php';
                            break;
                        case 33:
                            include 'query/sp_q33.php';
                            break;
                        case 34:
                            include 'query/sp_q34.php';
                            break;
                        case 35:
                            include 'query/sp_q35.php';
                            break;
                        case 36:
                            $response["status"] = 6; // Test connessione riuscito
                            break;
                        case 37:
                            include 'query/sp_q37.php';
                            break;
                        case 38:
                            include 'query/sp_q38.php';
                            break;
                        case 39:
                            include 'query/sp_q39.php';
                            break;
                        case 40:
                            include 'query/sp_q40.php';
                            break;
                        case 41:
                            include 'query/sp_q41.php';
                            break;
                        case 42:
                            include 'query/sp_q42.php';
                            break;
                        case 43:
                            include 'query/sp_q43.php';
                            break;
                        case 44:
                            include 'query/sp_q44.php';
                            break;
                        case 45:
                            include 'query/sp_q45.php';
                            break;
                        case 46:
                            include 'query/sp_q46.php';
                            break;
                        case 47:
                            include 'query/sp_q47.php';
                            break;
                        case 48:
                            include 'query/sp_q48.php';
                            break;
                        case 49:
                            include 'query/sp_q49.php';
                            break;
                        case 50:
                            include 'query/sp_q50.php';
                            break;
                        case 51:
                            include 'query/sp_q51.php';
                            break;
                        case 52:
                            include 'query/sp_q52.php';
                            break;
                        case 53:
                            include 'query/sp_q53.php';
                            break;
                        case 54:
                            include 'query/sp_q54.php';
                            break;
                        case 55:
                            include 'query/sp_q55.php';
                            break;
                        case 56:
                            include 'query/sp_q56.php';
                            break;
                        case 57:
                            include 'query/sp_q57.php';
                            break;
                        case 58:
                            include 'query/sp_q58.php';
                            break;
                        case 59:
                            include 'query/sp_q59.php';
                            break;
                        case 60:
                            include 'query/sp_q60.php';
                            break;
                        case 61:
                            include 'query/sp_q61.php';
                            break;
                        case 62:
                            include 'query/sp_q62.php';
                            break;
                        case 63:
                            include 'query/sp_q63.php';
                            break;
                        case 64:
                            include 'query/sp_q64.php';
                            break;
                        case 65:
                            include 'query/sp_q65.php';
                            break;
                        case 66:
                            include 'query/sp_q66.php';
                            break;
                        case 67:
                            include 'query/sp_q67.php';
                            break;
                        case 68:
                            include 'query/sp_q68.php';
                            break;
                        case 69:
                            include 'query/sp_q69.php';
                            break;
                        case 70:
                            include 'query/sp_q70.php';
                            break;
                        case 71:
                            include 'query/sp_q71.php';
                            break;
                    }

                } else {
                    $response["status"] = 3;
                }

            } else {
                $response["status"] = 5; // La versione del software non è la stessa dell'hosting PHP
            }

        } else {
            $response["status"] = 4; //Impossibile determinare la versione del software in utilizzo
        }

    } else {
        $response["status"] = 2;
    }

} else {
    $response["status"] = 1;
    $response["error"] = "JSON data not send!";
}

echo json_encode($response);

?>
