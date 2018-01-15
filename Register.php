<?php
    
    $con = mysqli_connect("localhost","id4164152_razvancrisan","tuborg","id4164152_users");
    
    $email = $_POST["email"];
    $password = $_POST["password"];
    $name = $_POST["name"];
    $weight = $_POST["weight"];
    $age = $_POST["age"];
    $genre = $_POST["genre"];
    
    $statement = mysqli_prepare($con, "INSERT INTO user (email,password,name,weight,age,genre) VALUES (?,?,?,?,?,?)");
    mysqli_stmt_bind_param($statement,"siss",$email,$password,$name,$weight,$age,$genre);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;
    
    echo json_encode($response);
?>