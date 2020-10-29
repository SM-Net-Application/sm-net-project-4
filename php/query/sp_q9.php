<?php
// Get all members
require_once dirname(__DIR__, 1) . '/config.php';
$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
mysqli_set_charset ( $database, 'utf8' );
if (! $database) {
	$response ["status"] = 4;
	$response ["error"] = mysqli_connect_error ();
} else {
	$query = "SELECT spMemberID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9";
	$query .= ", spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20";
	$query .= ", spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29, spInf30, spInf31";
	$query .= ", spInf32, spInf33, spInf34, spInf35, spInf36, spInf37, spInf38, spInf39, spInf40, spInf41, spInf42";
	$query .= ", spInf43, spInf44, spInf45, spInf46, spInf47, spInf48, spInf49, spInf50, spInf51, spInf52, spInf53";
	$query .= ", spInf54, spInf55, spInf56, spInf57, spInf58, spInf59";
	$query .= " FROM sp_members";
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
			$row ["spInf30"] = $resultRow ["spInf30"];
			$row ["spInf31"] = $resultRow ["spInf31"];
			$row ["spInf32"] = $resultRow ["spInf32"];
			$row ["spInf33"] = $resultRow ["spInf33"];
			$row ["spInf34"] = $resultRow ["spInf34"];
			$row ["spInf35"] = $resultRow ["spInf35"];
			$row ["spInf36"] = $resultRow ["spInf36"];
			$row ["spInf37"] = $resultRow ["spInf37"];
			$row ["spInf38"] = $resultRow ["spInf38"];
			$row ["spInf39"] = $resultRow ["spInf39"];
			$row ["spInf40"] = $resultRow ["spInf40"];
			$row ["spInf41"] = $resultRow ["spInf41"];
			$row ["spInf42"] = $resultRow ["spInf42"];
			$row ["spInf43"] = $resultRow ["spInf43"];
			$row ["spInf44"] = $resultRow ["spInf44"];
			$row ["spInf45"] = $resultRow ["spInf45"];
			$row ["spInf46"] = $resultRow ["spInf46"];
			$row ["spInf47"] = $resultRow ["spInf47"];
            $row ["spInf48"] = $resultRow ["spInf48"];
            $row ["spInf49"] = $resultRow ["spInf49"];
            $row ["spInf50"] = $resultRow ["spInf50"];
            $row ["spInf51"] = $resultRow ["spInf51"];
            $row ["spInf52"] = $resultRow ["spInf52"];
			$row ["spInf53"] = $resultRow ["spInf53"];
			$row ["spInf54"] = $resultRow ["spInf54"];
			$row ["spInf55"] = $resultRow ["spInf55"];
			$row ["spInf56"] = $resultRow ["spInf56"];
			$row ["spInf57"] = $resultRow ["spInf57"];
			$row ["spInf58"] = $resultRow ["spInf58"];
			$row ["spInf59"] = $resultRow ["spInf59"];
			
			array_push ( $response ["result"], $row );
		}
	} else {
		$response ["status"] = 5;
	}
	$result->close ();
	mysqli_close ( $database );
}

?>