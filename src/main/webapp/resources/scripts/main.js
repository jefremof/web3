var redraw;
var canvas;
var points_radius = [[],[],[],[],[]];
var xgraph;
var ygraph;
var graphset;
var graphform;
var circle;
var rectangle;
var triangle1;
var triangle2;
var ycord_message = $("#form\\:ycord-message");

function getPointColor(r,v) {
    var color = "#333"
    console.log(r,checkedR)
    if (r == checkedR) {
        if (v) {
            color = "#2f0"
        } else {
            color = "#f00";
        }
    }
    return color;
}

var redraw_figure;

$(document).ready(function() {
    
    graphset = $('#form2\\:graph-check');
    xgraph = $('#form2\\:graph-x');
    ygraph = $('#form2\\:graph-y');
    canvas = document.getElementById("graph");
    graphform = $('#form2\\:graph-submit');
    canvas.width = 600;
    canvas.height = 600;

    grid = new Grid(canvas);
    grid.settings.scroll.enabled = false;
    grid.settings.zoom.enabled = false;
    grid.settings.grid_lines.minor.spacing = 0.5;
    grid.settings.grid_lines.major.spacing = 0.5
    grid.settings.grid_lines.minor.width = 0;
    grid.settings.grid_lines.minor.colour = '#fff';
    grid.settings.grid_lines.major.colour = '#ddd';
    grid.settings.axes.width = 1;
    grid.settings.border.width=1;
    grid.settings.border.colour="#fff";
    grid.redraw();
    grid.drawAxes();


    redraw_figure = function() {
        if (rectangle!=undefined) grid.removeObject(rectangle);
        if (triangle1!=undefined) grid.removeObject(triangle1);
        if (circle!=undefined) grid.removeObject(circle);
        if (triangle2!=undefined) grid.removeObject(triangle2);
        rectangle = grid.addShape([[0,0],[checkedR/2,0],[checkedR/2,checkedR/4],[0,checkedR/4]],{"colour":"#39f","fill":true});
        triangle1 = grid.addShape([[0,0],[0,checkedR/2],[-checkedR/2,0]],{"colour":"#39f","fill":true});
        circle = grid.addFunction(
            function(x) {
                return -Math.sqrt(Math.pow((checkedR/2),2)-Math.pow(x,2));
            },
            {"interval": [0, checkedR/2]},{"colour":"#39f","fill":true}
        );
        triangle2 = grid.addShape([[0,0],[checkedR/2,0],[checkedR/2-0.01,-0.05],[0.05,-checkedR/2+0.01],[0,-checkedR/2]],{"colour":"#39f","fill":true});


        for (var i=-5; i<=5; i++) {
            grid.addText(i, i/2+0.05, 0.1, "left",
                         {"colour": "black", "font_size":15});
            if (i!=0) {
                grid.addText(i, 0.05, i/2+0.08, "left",
                             {"colour": "black", "font_size":15});
            }
        }
        grid.drawAxes();
    };

    if (checkedR!=-1) {
        grid.redraw();
        redraw_figure();
    }


    points.forEach(function (point) {
        p = grid.addCircle(point.x/2, point.y/2, 0.05, {"colour": getPointColor(point.r,point.v), "fill": true});
        grid.setZCoord(p,3);
        points_radius[point.r-1].push(p);
        grid.getObject(p).verdict = point.v;
    });



    canvas.addEventListener('click', (event) => {
        let point_cords = grid.fromCanvasCoords(event.clientX-canvas.offsetLeft, event.clientY-canvas.getBoundingClientRect().top);
        let point_x = (point_cords[0]*2);
        let point_y = (point_cords[1]*2);
        if (point_x>3 || point_x<-5) {
            if (graphset != undefined) {
                graphset.prop('checked',false);
            }
            return;
        }
        if (point_y>5 || point_y<-5) {
            if (graphset != undefined) {
                graphset.prop('checked',false);
            }
            reutrn;
        }
        if (graphset != undefined) {
            graphset.prop('checked',true);
        }
        if (xgraph != undefined) {
            xgraph.val(point_x);
        }
        if (ygraph != undefined) {
            ygraph.val(point_y);
        }
        console.log(xgraph.val(),ygraph.val(),graphset.prop('checked'));
        if (graphform != undefined) {
            graphform.click();
        }
    });

});

redraw = function() {
    if (grid!=undefined) {
        
        if (redraw !=undefined) {
            redraw_figure();
        }
    }
    let color;
    for(var i=0; i<points_radius.length; i++) {
        points_radius[i].forEach(function (shape) {
            if (points==[]) {
                grid.removeObject(shape);
            } else {
                point = grid.getObject(shape);
                point.style.colour=getPointColor(i+1,point.verdict);
            }
        })
    }

    if (points==[]) points_radius = [[],[],[],[],[]];
    console.log("redrawn");
    if (grid!=undefined) {
        grid.redraw();
        grid.drawAxes();
    }
}