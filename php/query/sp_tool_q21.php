<?php
// Get all weeks
if (isset ( $jsonObj ["keyStart"] ) && isset ( $jsonObj ["keyEnd"] )) {
	if (! empty ( $jsonObj ["keyStart"] ) && ! empty ( $jsonObj ["keyEnd"] )) {
		require_once __DIR__ . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $conn, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "SELECT sp_week.spWeekID, sp_week.spInf1, sp_week.spInf2, sp_week.spInf3, sp_week.spInf4,";
			$query .= " sp_week.spInf5, sp_week.spInf6, sp_week.spInf7, sp_week.spInf8, sp_week.spInf9, sp_week.spInf10,";
			$query .= " sp_week.spInf11, sp_week.spInf12, sp_week.spInf13, sp_week.spInf14, sp_week.spInf15, sp_week.spInf16,";
			$query .= " sp_week.spInf17, sp_week.spInf18, sp_week.spInf19, sp_week.spInf20, sp_week.spInf21, sp_week.spInf22,";
			$query .= " sp_week.spInf23, sp_week.spInf24, sp_week.spInf25, sp_week.spInf26, sp_week.spInf27, sp_week.spInf28,";
			$query .= " sp_week_ov.spWeekOvID, sp_week_ov.spInf1 as spInf1_ov, sp_week_ov.spInf2 as spInf2_ov, sp_week_ov.spInf3 as spInf3_ov,";
			$query .= " sp_week_ov.spInf4 as spInf4_ov, sp_week_ov.spInf5 as spInf5_ov, sp_week_ov.spInf6 as spInf6_ov,";
			$query .= " sp_week_ov.spInf7 as spInf7_ov, sp_week_ov.spInf8 as spInf8_ov, sp_week_ov.spInf9 as spInf9_ov,";
			$query .= " sp_week_ov.spInf10 as spInf10_ov, sp_week_ov.spInf11 as spInf11_ov, sp_week_ov.spInf12 as spInf12_ov,";
			$query .= " sp_week_ov.spInf13 as spInf13_ov, sp_week_ov.spInf14 as spInf14_ov, sp_week_ov.spInf15 as spInf15_ov";
			$query .= " FROM sp_week";
			$query .= " LEFT JOIN sp_week_ov";
			$query .= " ON sp_week.spInf1 = sp_week_ov.spInf1";
			//$query .= " WHERE sp_week.spInf1 BETWEEN";
			//$query .= " " . $jsonObj ["keyStart"];
			//$query .= " AND";
			//$query .= " " . $jsonObj ["keyEnd"];
			$query .= " ORDER BY sp_week.spInf1";
			
			$result = mysqli_query ( $database, $query );
			
			if (mysqli_num_rows ( $result ) > 0) {
				$response ["status"] = 0;
				$response ["result"] = array ();
				while ( $resultRow = $result->fetch_assoc () ) {
					
					$resultRow = array_map ( "utf8_encode", $resultRow );
					
					$row = array ();
					$row ["spWeekID"] = $resultRow ["spWeekID"];
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
					
					// Circuit Overseer
					$row ["spWeekOvID"] = $resultRow ["spWeekOvID"];
					$row ["spInf1_ov"] = $resultRow ["spInf1_ov"];
					$row ["spInf2_ov"] = $resultRow ["spInf2_ov"];
					$row ["spInf3_ov"] = $resultRow ["spInf3_ov"];
					$row ["spInf4_ov"] = $resultRow ["spInf4_ov"];
					$row ["spInf5_ov"] = $resultRow ["spInf5_ov"];
					$row ["spInf6_ov"] = $resultRow ["spInf6_ov"];
					$row ["spInf7_ov"] = $resultRow ["spInf7_ov"];
					$row ["spInf8_ov"] = $resultRow ["spInf8_ov"];
					$row ["spInf9_ov"] = $resultRow ["spInf9_ov"];
					$row ["spInf10_ov"] = $resultRow ["spInf10_ov"];
					$row ["spInf11_ov"] = $resultRow ["spInf11_ov"];
					$row ["spInf12_ov"] = $resultRow ["spInf12_ov"];
					$row ["spInf13_ov"] = $resultRow ["spInf13_ov"];
					$row ["spInf14_ov"] = $resultRow ["spInf14_ov"];
					$row ["spInf15_ov"] = $resultRow ["spInf15_ov"];
					
					// Ho bisogno della WeekCode
					$spInf1 = $resultRow ["spInf1"];
					
					// Devo caricare le parti ministero
					$row ["spInfMIN"] = array ();
					// Query
					$query_min = "SELECT spWeekMinID, spInf1, spInf2, spInf3, spInf4, spInf5,";
					$query_min .= " spInf6, spInf7, spInf8, spInf9, spInf10";
					$query_min .= " FROM sp_week_min";
					$query_min .= " WHERE  spInf1='" . $spInf1 . "'";
					$query_min .= " ORDER BY spInf2";
					// Eseguo la query
					$result_min = mysqli_query ( $database, $query_min );
					if (mysqli_num_rows ( $result_min ) > 0) {
						while ( $resultRow_min = $result_min->fetch_assoc () ) {
							
							$resultRow_min = array_map ( "utf8_encode", $resultRow_min );
							
							$row_min = array ();
							$row_min ["spWeekMinID"] = $resultRow_min ["spWeekMinID"];
							$row_min ["spInf1"] = $resultRow_min ["spInf1"];
							$row_min ["spInf2"] = $resultRow_min ["spInf2"];
							$row_min ["spInf3"] = $resultRow_min ["spInf3"];
							$row_min ["spInf4"] = $resultRow_min ["spInf4"];
							$row_min ["spInf5"] = $resultRow_min ["spInf5"];
							$row_min ["spInf6"] = $resultRow_min ["spInf6"];
							$row_min ["spInf7"] = $resultRow_min ["spInf7"];
							$row_min ["spInf8"] = $resultRow_min ["spInf8"];
							$row_min ["spInf9"] = $resultRow_min ["spInf9"];
							$row_min ["spInf10"] = $resultRow_min ["spInf10"];
							
							array_push ( $row ["spInfMIN"], $row_min );
						}
					}
					
					// Devo caricare le parti vita cristiana
					$row ["spInfCR"] = array ();
					// Query
					$query_cr = "SELECT spWeekCrID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6";
					$query_cr .= " FROM sp_week_cr";
					$query_cr .= " WHERE  spInf1='" . $spInf1 . "'";
					$query_cr .= " ORDER BY spInf2";
					// Eseguo la query
					$result_cr = mysqli_query ( $database, $query_cr );
					if (mysqli_num_rows ( $result_cr ) > 0) {
						while ( $resultRow_cr = $result_cr->fetch_assoc () ) {
							
							$resultRow_cr = array_map ( "utf8_encode", $resultRow_cr );
							
							$row_cr = array ();
							$row_cr ["spWeekCrID"] = $resultRow_cr ["spWeekCrID"];
							$row_cr ["spInf1"] = $resultRow_cr ["spInf1"];
							$row_cr ["spInf2"] = $resultRow_cr ["spInf2"];
							$row_cr ["spInf3"] = $resultRow_cr ["spInf3"];
							$row_cr ["spInf4"] = $resultRow_cr ["spInf4"];
							$row_cr ["spInf5"] = $resultRow_cr ["spInf5"];
							$row_cr ["spInf6"] = $resultRow_cr ["spInf6"];
							
							array_push ( $row ["spInfCR"], $row_cr );
						}
					}
					
					// Carico la riga nel risultato
					array_push ( $response ["result"], $row );
				}
			} else {
				$response ["status"] = 5;
			}
			$result->close ();
			mysqli_close ( $database );
		}
	} else {
		$response ["status"] = 6;
	}
} else {
	$response ["status"] = 6;
}