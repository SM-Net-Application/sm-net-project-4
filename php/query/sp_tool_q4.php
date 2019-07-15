<?php
// Get all users no SU
require_once __DIR__ . '/config.php';
$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
mysqli_set_charset ( $conn, 'utf8' );
if (! $database) {
	$response ["status"] = 4;
	$response ["error"] = mysqli_connect_error ();
} else {
	$query = "SELECT spUserID, spUserSU, spUserName, spUserPassword, spInf1, spInf2, spInf3, spInf4, spInf5 FROM sp_users WHERE spUserSU=0";
	$result = mysqli_query ( $database, $query );
	
	if (mysqli_num_rows ( $result ) > 0) {
		$response ["status"] = 0;
		$response ["result"] = array ();
		while ( $resultRow = $result->fetch_assoc () ) {
			$resultRow = array_map ( "utf8_encode", $resultRow );
			$row = array ();
			$row ["spUserID"] = $resultRow ["spUserID"];
			$row ["spUserSU"] = $resultRow ["spUserSU"];
			$row ["spUserName"] = $resultRow ["spUserName"];			
			$row ["spUserPassword"] = $resultRow ["spUserPassword"];
			$row ["spInf1"] = $resultRow ["spInf1"];
			$row ["spInf2"] = $resultRow ["spInf2"];
			$row ["spInf3"] = $resultRow ["spInf3"];
			$row ["spInf4"] = $resultRow ["spInf4"];
			$row ["spInf5"] = $resultRow ["spInf5"];
			array_push ( $response ["result"], $row );
		}
	} else {
		$response ["status"] = 5;
	}
	$result->close ();
	mysqli_close ( $database );
}

?>