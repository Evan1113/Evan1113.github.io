/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


var rheight = 45;
//var chartDataArray=[100, 27, 133, 19, 23, 76, 42, 58, 45,66,33];
//let p="100, 27, 133, 19, 23, 76, 42, 58, 45,66,33";
var chartDataArray=null;
let wedstate = ["全部連結","有關鍵字連結","沒有關鍵字連結","錯誤連結"];
function displayChart(){

    d3.selectAll(".chart > *").remove(); 
    var chart = d3.select('.chart')
            .attr("width",600)
            .attr('height',420)
            .attr('margin',5);
    
    var bar = chart.selectAll("g")
            .data(chartDataArray)
            .enter().append("g");

    bar.append("rect")
        .attr("y",function(d,i){return 300-(d*2.8);})
        .attr("x",function(d,i){
             return i * rheight;
        })
        .attr("height",function(d){
             return (d*2.8);
        })
        .attr("width",rheight-3)
        .attr("fill","#5F4B8B")
            .attr("stroke-width",2)
            .attr("stroke","black");


    bar.append('text')
            .attr('y',function(d){return 300-(d*2.8)+21;})
            .attr("x",function(d,i){
             return i * rheight+22;
        })
            .style('fill', '#FFF')
            .style('font-size', '18px')
            .style('font-weight', 'bold')
            .style("text-anchor", 'middle')
            .text(function(d){
            return d;}
                     ); 
 
    bar.append('text')
            .attr('y', 305)
            .attr('x',function(d,i){
                    return i * rheight + 22;
            })
            .style('fill', 'black')
            .style('font-size', '16px')
            .style('font-weight', 'bold')
//            .style("text-anchor", 'middle')
            .style("writing-mode", "vertical-lr")
            .data(wedstate)
            .text(function(d){
            return d;});
}          

function getChartDataAndShow(){
        
        var urlChart="CSVservlet"+            // servlet 
                "?actionID=getChartData";   // parameters";
        var y=null;        
                
        let z=fetch(urlChart)
            .then((response) => response.text())
            .then(x=>{
                
                y=x.split(",");
                chartDataArray=y;
                displayChart();        
            });    

}

