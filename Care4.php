<?php
//Note Verifier le port de la connection anvant execution
$dsn = 'mysql:host=localhost;dbname=care4_db;port=3306';
$userName='root';
$password='';
global $database;


try{
$database = new PDO($dsn, $userName, $password);

} catch(Exeption $e){
	$error = $e->getMessage();
	echo 'Error de connection PDO';
	echo($error);
}

$action = $_REQUEST['action'];
switch ($action) {
	case 'creeCompte':
	creeCompte();
		break;
	case 'login':
	login();
		break;
	case 'updateProfile':
	updateProfile();
		break;
	default:
		# code...
		break;
}


function creeCompte(){
	global $database;
	echo 'in creeCompte';

	$nom = urldecode ($_REQUEST['nom']);
	$prenom = urldecode ($_REQUEST['prenom']);
	$username = urldecode ($_REQUEST['username']);
	$email= urldecode ($_REQUEST['email']);
	$telephone = urldecode ($_REQUEST['telephone']);
	$password = urldecode ($_REQUEST['password']);
	$type = urldecode ($_REQUEST['type']);
	

	$sql = 'INSERT INTO profile (nom, prenom, telephone, email, nomUtilisateur, type, motDePasse) VALUES (:nom, :prenom, :telephone, :email, :username, :type, :password)';

	$stmt = $database->prepare($sql);

	$stmt->bindParam(':nom', $nom);
	$stmt->bindParam(':prenom', $prenom);
	$stmt->bindParam(':username', $username);
	$stmt->bindParam(':email', $email);
	$stmt->bindParam(':telephone', $telephone);
	$stmt->bindParam(':password', $password);
	$stmt->bindParam(':type', $type);
	
	$stmt->execute();

}

function updateCompte(){
	global $database;
	echo 'in creeCompte';

	$nom = urldecode ($_REQUEST['nom']);
	$prenom = urldecode ($_REQUEST['prenom']);
	$username = urldecode ($_REQUEST['username']);
	$email= urldecode ($_REQUEST['email']);
	$telephone = urldecode ($_REQUEST['telephone']);
	$password = urldecode ($_REQUEST['password']);
	$type = urldecode ($_REQUEST['type']);
	

	$sql = 'UPDATE profile  SET nom = :nom , prenom = :prenom, telephone = :telephone, email = :email, nomUtilisateur = :username , type = :type, motDePasse =:password WHERE nomUtilisateur = :username';

	$stmt = $database->prepare($sql);

	$stmt->bindParam(':nom', $nom);
	$stmt->bindParam(':prenom', $prenom);
	$stmt->bindParam(':username', $username);
	$stmt->bindParam(':email', $email);
	$stmt->bindParam(':telephone', $telephone);
	$stmt->bindParam(':password', $password);
	$stmt->bindParam(':type', $type);
	


	$stmt->execute();
	// Test Return echo 'okidoki' . $telephone . $nom . $prenom . $email . $username.$type.$password;

}



function login(){
	
	global $database;

	$username = $_REQUEST['username'];
	$password = $_REQUEST['password'];

	$sql = 'SELECT * FROM utilisateurs
	WHERE nomUtilisateur = :username
	AND motDePasse = :password';

	$stmt = $database->prepare($sql);
	$stmt->bindParam(':username', $username);
	$stmt->bindParam(':password', $password);

	
	$stmt->execute();
	$result = $stmt->fetch();
	
	$myType = $result['type'];
	//$myUsername = $result['nomUtilisateur'];

	echo $myType;
	
}



?>