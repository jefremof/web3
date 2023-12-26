$(document).ready(function () {
    var counter = 0;
        function getTime() {
            let datetime= new Date().toLocaleString();
            let [date, time] = datetime.split(',');
            $('#clock-time').html(time);
            $('#clock-date').html(date);
            let origin_fs = $('#clock-date').css("fontSize");
            $('#clock-time').animate({fontSize: '84pt'}, 100);
            $('#clock-time').animate({fontSize: '80pt'}, 200);
        }
        function updateTime() {
            counter += 1;
            if (counter%5==0) {
                getTime();
            } else {
//                $('#clock-time').animate({fontSize: '81pt'}, 50);
//                $('#clock-time').animate({fontSize: '80pt'}, 100);
            }
        }
        getTime();
        setInterval(updateTime, 1000);
});