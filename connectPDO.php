<?php
$dsn = 'mysql:host=localhost;dbname=care4_db;port=3306';
$userName='root';
$password='';
$action;

global $database;



try{
$database = new PDO($dsn, $userName, $password);

} catch(Exeption $e){
	$error = $e->getMessage();
	echo($error);
}

//addLoginTest();

//addUserTest();

//addLoginTest();

login();

function test(){
	global $database;
	$sql = 'SELECT * FROM login';
	$result = $database->query($sql);
	$user = $result->fetch();
	var_dump($user);
	echo $result->queryString;
}

function addUserTest(){
	global $database;
	$sql = "INSERT INTO login VALUES ('Tom', 'Sauyer')";
	$database->query($sql);
}

function addLoginTest(){
	global $database;
	//$username  = urldecode ($_REQUEST['username']);
	//$password = urldecode ($_REQUEST['password']);

	//$username  = urldecode ($_POST['username']);
	//$password = urldecode ($_POST['password']);

	//$username  = 'asd';
	//$password = 'ksafd';

	//$username=$_REQUEST['username'];
	//$password=$_REQUEST['password'];

	//$sql = 'INSERT INTO login VALUES (:username, :password)';
	$sql = 'INSERT INTO login VALUES (:username, :password)';
	$stmt = $database->prepare($sql);
	$stmt->bindParam(':username', $username);
	$stmt->bindParam(':password', $password);
	$stmt->execute();



}

functiom 

function creeCompte(){
	$nom=$_REQUEST['nom'];
	$prenom=$_REQUEST['prenom'];
	//$age=$_REQUEST['age'];
	//$sexe=$_REQUEST['sexe'];
	$adresse=$_REQUEST['adresse'];
	$username=$_REQUEST['username'];
	$email=$_REQUEST['email'];
	$telephone=$_REQUEST['telephone'];
	$password=$_REQUEST['password'];
	$type=$_REQUEST['type'];
	

	$sql = 'INSERT INTO utilisateurs (nom,prenom,telephone,email,adresse,nomUtilisateur) VALUES(:nom,:prenom,:telephone,:email,:adresse,:username) ';

	$stmt = $database->prepare($sql);
	$stmt->bindParam(':nom', $nom);
	$stmt->bindParam(':prenom', $prenom);
	//$stmt->bindParam(':sexe', $sexe);
	//$stmt->bindParam(':age', $age, PDO::PARAM_INT);
	$stmt->bindParam(':username', $username);
	$stmt->bindParam(':email', $email);
	$stmt->bindParam(':telephone', $telephone);
	$stmt->bindParam(':password', $password);
	$stmt->bindParam(':type', $type);
	$stmt->bindParam(':adresse', $adresse);

	$stm->execute();

}

function login(){
	global $database;
	$username = $_REQUEST['username'];
	$password = $_REQUEST['password'];

	$sql = 'SELECT * FROM login
	WHERE username = :username,
	AND password = :password';

	$stmt = $database->prepare($sql);
	$stmt->bindParam(':username', $username);
	$stmt->bindParam(':password', $password);
	$stmt->execute();

	$result = $stmt->fetchAll();



	//echo $result->type;
	echo $_REQUEST['username'];;
	//echo $result['type'];

	//$result = $database->query($sql);
	//$numrows = $result->rowCount()
}

function updateProfile(){
	$nom=$_REQUEST['nom'];
	$prenom=$_REQUEST['prenom'];
	//$age=$_REQUEST['age'];
	//$sexe=$_REQUEST['sexe'];
	$adresse=$_REQUEST['adresse'];
	$username=$_REQUEST['username'];
	$email=$_REQUEST['email'];
	$telephone=$_REQUEST['telephone'];
	$password=$_REQUEST['password'];
	$type=$_REQUEST['type'];


	$sql = 'UPDATE utilisateurs
	SET 
	WHERE ';

	$stmt = $database->prepare($sql);
	$stm->bindParam(':nom', $nom);
	$stm->bindParam(':prenom', $prenom);
	
	//$stm->bindParam(':sexe', $sexe);
	//$stm->bindParam(':age', $age, PDO::PARAM_INT);
	$stm->bindParam(':username', $username);
	$stm->bindParam(':email', $email);
	$stm->bindParam(':telephone', $telephone);
	$stm->bindParam(':password', $password);
	$stm->bindParam(':type', $type);
	$stm->bindParam(':adresse', $adresse);

	$stm->execute();
	
}

function listerContact(){

}

/*$action=$_REQUEST['action'];
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
}*/