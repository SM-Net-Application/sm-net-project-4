<?php
// Get all members
require_once __DIR__ . '/config.php';
$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
mysqli_set_charset ( $conn, 'utf8' );
if (! $database) {
	$response ["status"] = 4;
	$response ["error"] = mysqli_connect_error ();
} else {
	$query = "SELECT spMemberID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9";
	$query .= ", spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20";
	$query .= ", spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29 FROM sp_members";
	$result = mysqli_query ( $database, $query );
	
	if (mysqli_num_rows ( $result ) > 0) {
		$response ["status"] = 0;
		$response ["result"] = array ();
		while ( $resultRow = $result->fetch_assoc () ) {
			$resultRow = array_map ( "utf8_encode", $resultRow );
			$row = array ();
			$row ["spMemberID"] = $resultRow ["spMemberID"];
			$row ["spInf1"] = $resultRow ["spInf1"];
			$row ["spInf2"] = $resultRow ["spInf2"];
			$row ["spInf3"] = $resultRow ["spInf3"];
			$row ["spInf4"] = $resultRow ["spInf4"];
			$row ["spInf5"] = $resultRow ["spInf5"];
			$row ["spInf6"] = $resultRow ["spInf6"];
			$row ["spInf7"] = $resultRow ["spInf7"];
			$row ["spInf8"] = $resultRow ["spInf8"];
			$row ["spInf9"] = $resultRow ["spInf9"];
			$row ["spInf10"] = $resultRow ["spInf10"];
			$row ["spInf11"] = $resultRow ["spInf11"];
			$row ["spInf12"] = $resultRow ["spInf12"];
			$row ["spInf13"] = $resultRow ["spInf13"];
			$row ["spInf14"] = $resultRow ["spInf14"];
			$row ["spInf15"] = $resultRow ["spInf15"];
			$row ["spInf16"] = $resultRow ["spInf16"];
			$row ["spInf17"] = $resultRow ["spInf17"];
			$row ["spInf18"] = $resultRow ["spInf18"];
			$row ["spInf19"] = $resultRow ["spInf19"];
			$row ["spInf20"] = $resultRow ["spInf20"];
			$row ["spInf21"] = $resultRow ["spInf21"];
			$row ["spInf22"] = $resultRow ["spInf22"];
			$row ["spInf23"] = $resultRow ["spInf23"];
			$row ["spInf24"] = $resultRow ["spInf24"];
			$row ["spInf25"] = $resultRow ["spInf25"];
			$row ["spInf26"] = $resultRow ["spInf26"];
			$row ["spInf27"] = $resultRow ["spInf27"];
			$row ["spInf28"] = $resultRow ["spInf28"];
			$row ["spInf29"] = $resultRow ["spInf29"];
			array_push ( $response ["result"], $row );
		}
	} else {
		$response ["status"] = 5;
	}
	$result->close ();
	mysqli_close ( $database );
}

?>