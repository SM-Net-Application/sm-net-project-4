<?php
// Get all service groups
require_once dirname(__DIR__, 1) . '/config.php';
$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
mysqli_set_charset ( $database, 'utf8' );
if (! $database) {
	$response ["status"] = 4;
	$response ["error"] = mysqli_connect_error ();
} else {
	$query = "SELECT S.spSerGrID, S.spInf1, S.spInf2, S.spInf3, S.spSerGroupFamilies, S.spSerGroupMembers,";
	$query .= " O.spInf1 AS O1, O.spInf2 AS O2, A.spInf1 AS A1, A.spInf2 AS A2";
	$query .= " FROM (";
	$query .= "SELECT sp_sergr.spSerGrID, sp_sergr.spInf1, sp_sergr.spInf2, sp_sergr.spInf3,";
	$query .= " COUNT(DISTINCT sp_fam.spFamID) AS spSerGroupFamilies, COUNT(DISTINCT sp_members.spMemberID) AS spSerGroupMembers";
	$query .= " FROM sp_sergr";
	$query .= " INNER JOIN sp_fam ON sp_sergr.spSerGrID = sp_fam.spInf6";
	$query .= " INNER JOIN sp_members ON sp_fam.spFamID = sp_members.spInf5";
	$query .= " GROUP BY sp_sergr.spSerGrID) AS S";
	$query .= " LEFT JOIN sp_members AS O ON O.spMemberID = S.spInf2";
	$query .= " LEFT JOIN sp_members AS A ON A.spMemberID = S.spInf3";
	
	$result = mysqli_query ( $database, $query );
	
	if (mysqli_num_rows ( $result ) > 0) {
		$response ["status"] = 0;
		$response ["result"] = array ();
		while ( $resultRow = $result->fetch_assoc () ) {
			$resultRow = array_map ( "utf8_encode", $resultRow );
			$row = array ();
			$row ["spSerGrID"] = $resultRow ["spSerGrID"];
			$row ["spInf1"] = $resultRow ["spInf1"];
			$row ["spInf2"] = $resultRow ["spInf2"];
			$row ["spInf3"] = $resultRow ["spInf3"];
			$row ["O1"] = $resultRow ["O1"];
			$row ["O2"] = $resultRow ["O2"];
			$row ["A1"] = $resultRow ["A1"];
			$row ["A2"] = $resultRow ["A2"];
			$row ["spSerGroupFamilies"] = $resultRow ["spSerGroupFamilies"];
			$row ["spSerGroupMembers"] = $resultRow ["spSerGroupMembers"];
			array_push ( $response ["result"], $row );
		}
	} else {
		$response ["status"] = 5;
	}
	$result->close ();
	mysqli_close ( $database );
}

?>