/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var win = window,
doc = document,
docElem = doc.documentElement,
body = doc.getElementsByTagName('body')[0];
        
x = win.innerWidth || docElem.clientWidth || body.clientWidth,
y = win.innerHeight|| docElem.clientHeight|| body.clientHeight;

function getWebMetrix() {
    document.getElementById("iframe_A").width=Math.floor(x*0.841).toString();  // 0.83 is defined for displaying target 
    document.getElementById("iframe_A").height=Math.floor(y*0.44).toString(); // 0.88 is the height of definedContent
}
        
function getFileName(){
    
    document.getElementById("fileName_1").value=document.getElementById("selectedFile").value;
    document.getElementById("fileName_2").value=document.getElementById("selectedFile").value;
    document.getElementById("fileName_3").value=document.getElementById("selectedFile").value;
    document.getElementById("fileName_4").value=document.getElementById("selectedFile").value;
    document.getElementById("fileName_5").value=document.getElementById("selectedFile").value;
    document.getElementById("fileName_6").value=document.getElementById("selectedFile").value;

}        

function getFieldName(){
    if (document.getElementById("iframe_fieldsList_2").contentWindow.document.getElementById("selectionFields").value != null){
//        alert(document.getElementById("iframe_fieldsList_2").contentWindow.document.getElementById("selectionFields").value);
        document.getElementById("fieldNo_2").value=document.getElementById("iframe_fieldsList_2").contentWindow.document.getElementById("selectionFields").value;
    }
}

function showSearchDIV(){
    document.getElementById("soupDiv").style.visibility="hidden";
    document.getElementById("soupDiv").style.width="0";
    document.getEle;mentById("soupDiv").style.height="0";    
    document.getElementById("serchDiv").style.visibility="visible";    
}

function showSoupDiv(){
    //document.getElementById("serchDiv").style.width="200px";
    //document.getElementById("serchDiv").style.height="100px";
    document.getElementById("serchDiv").style.visibility="hidden";
    document.getElementById("serchDiv").style.width="0";
    document.getElementById("serchDiv").style.height="0";
    document.getElementById("soupDiv").style.visibility="visible";   
    
}

function getText(){
        const uri="http://localhost/CSVservlet";
        const actionID="showFields";
        const fileName="product.csv";
        
        fetch (uri,{method:"GET"})
        .then(x => x.text())
        .then(y => document.getElementById("demo").innerHTML = y);
    
}
function openloadingDiv(){
        document.getElementById('loading').style.display='block';
}

function loadingDiv(){
        document.getElementById('loading').style.display='none';
}
//function AAA(){
//    alert("here");
// // set the dimensions and margins of the graph
//        var margin = {top: 30, right: 30, bottom: 70, left: 60},
//            width = 460 - margin.left - margin.right,
//            height = 400 - margin.top - margin.bottom;
//        
//        // append the svg object to the body of the page
//        var svg = d3.select("#my_dataviz")
//          .append("svg")
//            .attr("width", width + margin.left + margin.right)
//            .attr("height", height + margin.top + margin.bottom)
//          .append("g")
//            .attr("transform",
//                  "translate(" + margin.left + "," + margin.top + ")");
//        
//        // Parse the Data
//        d3.csv("e.csv", function(data) {
//        
//        // X axis
//        var x = d3.scaleBand()
//          .range([ 0, width ])
//          .domain(data.map(function(d) { return d.error; }))
//          .padding(0.2);
//        svg.append("g")
//          .attr("transform", "translate(0," + height + ")")
//          .call(d3.axisBottom(x))
//          .selectAll("text")
//            .attr("transform", "translate(-10,0)rotate(-45)")
//            .style("text-anchor", "end");
//        
//        // Add Y axis
//        var y = d3.scaleLinear()
//          .domain([0, 100])
//          .range([ height, 0]);
//        svg.append("g")
//          .call(d3.axisLeft(y));
//        
//        // Bars
//        svg.selectAll("mybar")
//          .data(data)
//          .enter()
//          .append("rect")
//            .attr("x", function(d) { return x(d.error); })
//            .attr("y", function(d) { return y(d.value); })
//            .attr("width", x.bandwidth())
//            .attr("height", function(d) { return height - y(d.value); })
//            .attr("fill", "#69b3a2")
//        
//        })
//}
