<?php

    $con = mysqli_connect("localhost","id4164152_razvancrisan","tuborg","id4164152_users");
    
    $email = $_POST["email"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE email = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $email, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $user_id, $name, $weight, $age, $genre, $email, $password);
    
    $response = array();
    $response["success"] = false;
    
    while (mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["name"] = $name;
        $response["weight"] = $weight;
        $response["age"] = $age;
        $response["genre"] = $genre;
        $response["email"] = $email;
        $response["password"] = $password;
    }
    
    echo json_encode($response);
?>