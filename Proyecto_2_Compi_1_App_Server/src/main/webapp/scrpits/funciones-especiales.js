/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//FUNCION ASCENDENTE
function ASC(palabra){
    var nuevaPalabra = "";
    
    nuevaPalabra = Array.from(palabra).sort(function (a, b) {
        return a.localeCompare(b);
    });
    
    return nuevaPalabra.join("");
    //alert("Nueva palabra: " + nuevaPalabra.join(""));
}

//FUNCION DESCENDENTE
function DESC(palabra){
    var nuevaPalabra = "";
    
    nuevaPalabra = Array.from(palabra).sort(function (b, a) {
        return a.localeCompare(b);
    });
    
    return nuevaPalabra.join("");
    //alert("Nueva palabra: " + nuevaPalabra.join(""));
}

//LETPAR
function LETPAR_NUM(palabra){
    let nuevaPalabra = "";
    var arregloPalabra = palabra.split("");
    
    for(var i = 0; i < arregloPalabra.length; i++){
        //const palabraAux = String.fromCharCode(arregloPalabra.indexOf(i));
        var palabraAux = arregloPalabra[i];
        if((i+1)%2 === 0){//par
            nuevaPalabra += palabraAux.charCodeAt(0);       
        }else{ 
            nuevaPalabra += palabraAux;
        }
    }
    
    return nuevaPalabra;
}

//LETIMPAR
function LETIMPAR_NUM(palabra){
    let nuevaPalabra = "";
    var arregloPalabra = palabra.split("");
    
    for(var i = 0; i < arregloPalabra.length; i++){
        //const palabraAux = String.fromCharCode(arregloPalabra.indexOf(i));
        var palabraAux = arregloPalabra[i];
        if((i+1)%2 === 1){//par
            nuevaPalabra += palabraAux.charCodeAt(0);       
        }else{ 
            nuevaPalabra += palabraAux;
        }
    }
    
    return nuevaPalabra;
}


//REVERSE, invertimos la palabra
function REVERSE(input){
    let reverseResult = "";
    for ( var i = input.length -1; i >= 0; i--) {
        reverseResult = reverseResult + input[i];
    }
    return reverseResult;
}

//RANDOM CHAR
function CARACTER_ALEATORIO(){
    let random_string = '';
    let random_ascii;
    let ascii_low = 65;
    let ascii_high = 90
    
    random_ascii = Math.floor((Math.random() * (ascii_high - ascii_low)) + ascii_low);
    let tipo = Math.floor((Math.random() * 2));
    if(tipo >= 1 ){
        random_ascii += 32;
    }
    
    random_string += String.fromCharCode(random_ascii);
    
    return random_string;
}

//RANDOM NUM
function NUM_ALEATORIO(){
    let random_ascii;
    let ascii_low = 0;
    let ascii_high = 9
    
    random_ascii = Math.floor((Math.random() * (ascii_high - ascii_low)) + ascii_low);
    
    return random_ascii;
}

//REDIRECT
function REDIRECT(direccion){
    window.location.replace(direccion);    
}