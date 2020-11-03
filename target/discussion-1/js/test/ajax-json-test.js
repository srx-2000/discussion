var data = {
    "userId": 1,
    "username": "srx",
    "password": "srx62600",
    "email": "1601684622@qq.com",
    "authority": "1"
}
$("#cilck").click(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/discussion_war_exploded/testJson",
        contentType: "application/json;charset=UTF-8",
        dataType:"html",
        success: function (data) {
            console.log(data)
            $("")
        }
    })
})

