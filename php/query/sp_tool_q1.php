<?php
// Count users in database
require_once __DIR__ . '/config.php';
$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
mysqli_set_charset ( $database, 'utf8' );
if (! $database) {
	$response ["status"] = 4;
	$response ["error"] = mysqli_connect_error ();
} else {
	$query = "SELECT COUNT(spUserID) AS users FROM sp_users";
	$result = mysqli_query ( $database, $query );
	if (mysqli_num_rows ( $result ) > 0) {
		$response ["status"] = 0;
		$response ["result"] = array ();
		while ( $resultRow = $result->fetch_assoc () ) {
			$resultRow = array_map ( "utf8_encode", $resultRow );
			$row = array ();
			$row ["users"] = $resultRow ["users"];
			array_push ( $response ["result"], $row );
		}
	} else {
		$response ["status"] = 5;
	}
	$result->close ();
	mysqli_close ( $database );
}
?>