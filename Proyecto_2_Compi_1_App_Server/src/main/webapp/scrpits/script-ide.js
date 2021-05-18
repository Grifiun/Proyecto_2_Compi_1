var entrada = "";
var entInput = document.getElementById('input');
var entTexto = document.getElementById('texto');

entInput.addEventListener('change', function() {
    var fr;
    fr = null;
    fr  = new FileReader();
    
    fr.readAsText(this.files[0], 'ISO-8859-1');  
    this.files = null;
    
    fr.onload = function(){
        //document.getElementById('texto')
         //       .textContent = fr.result;
        
        entrada = fr.result;
        //alert(entrada);        
        checkNum(entrada);
        
        entTexto.innerHTML = entrada.normalize('NFD').replace(/[\u0300-\u036f]/g, "");
    }
    
})
  
  
function checkNum(text) {
    //Now check
    text = text  + '';
    var lines = text.split(/\r|\r\n|\n/);
    var count = lines.length;
    //console.log("linea: "+count);
    
    
    var newLineCount = "";
    
    for(var i = 1; i <= count; i++){
        newLineCount += i;
        if(i != count){
            //salto de linea si no es el ultimo
            newLineCount += '\n';
        }
        
    }
    
    newLineCount.trim();
    //alert(newLineCount);
    
    //cambiamos el contador
    document.getElementById("contador").innerHTML = newLineCount;
}


function download() {
  var text =  entTexto.value;
  var name = prompt("Nombre del archivo","nombre_de_ejemplo");
  var filename = name+".gcic";
  
  if(name == null){    
    alert("Se ha cancelado la descarga");
    
  }else{
    var element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
    element.setAttribute('download', filename);

    element.style.display = 'none';
    document.body.appendChild(element);

    element.click();
    document.body.removeChild(element);
      
  }
  
}