<?php
// Get all families
require_once __DIR__ . '/config.php';
$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
mysqli_set_charset ( $conn, 'utf8' );
if (! $database) {
	$response ["status"] = 4;
	$response ["error"] = mysqli_connect_error ();
} else {
	$query = "SELECT sp_fam.spFamID, sp_fam.spInf1, sp_fam.spInf2, sp_fam.spInf3, sp_fam.spInf4, sp_fam.spInf5, sp_fam.spInf6,";
	$query .= " COUNT(sp_members.spMemberID) AS spFamMembers";
	$query .= " FROM sp_fam";
	$query .= " INNER JOIN sp_members";
	$query .= " ON sp_fam.spFamID = sp_members.spInf5";
	$query .= " GROUP BY sp_fam.spFamID";
	
	$result = mysqli_query ( $database, $query );
	
	if (mysqli_num_rows ( $result ) > 0) {
		$response ["status"] = 0;
		$response ["result"] = array ();
		while ( $resultRow = $result->fetch_assoc () ) {
			$resultRow = array_map ( "utf8_encode", $resultRow );
			$row = array ();
			$row ["spFamID"] = $resultRow ["spFamID"];
			$row ["spInf1"] = $resultRow ["spInf1"];
			$row ["spInf2"] = $resultRow ["spInf2"];
			$row ["spInf3"] = $resultRow ["spInf3"];
			$row ["spInf4"] = $resultRow ["spInf4"];
			$row ["spInf5"] = $resultRow ["spInf5"];
			$row ["spInf6"] = $resultRow ["spInf6"];
			$row ["spFamMembers"] = $resultRow ["spFamMembers"];
			array_push ( $response ["result"], $row );
		}
	} else {
		$response ["status"] = 5;
	}
	$result->close ();
	mysqli_close ( $database );
}

?>