<?php
// Get all users
require_once __DIR__ . '/config.php';
$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
mysqli_set_charset ( $conn, 'utf8' );
if (! $database) {
	$response ["status"] = 4;
	$response ["error"] = mysqli_connect_error ();
} else {
	$query = "SELECT spUserID, spUserName, spRoleAdmin FROM sp_users";
	$result = mysqli_query ( $database, $query );
	
	if (mysqli_num_rows ( $result ) > 0) {
		$response ["status"] = 0;
		$response ["result"] = array ();
		while ( $resultRow = $result->fetch_assoc () ) {
			$resultRow = array_map ( "utf8_encode", $resultRow );
			$row = array ();
			$row ["spUserID"] = $resultRow ["spUserID"];
			$row ["spUserName"] = $resultRow ["spUserName"];
			$row ["spRoleAdmin"] = $resultRow ["spRoleAdmin"];
			array_push ( $response ["result"], $row );
		}
	} else {
		$response ["status"] = 5;
	}
	$result->close ();
	mysqli_close ( $database );
}

?>