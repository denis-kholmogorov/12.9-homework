$(function(){

     const appendTask = function(data){
            var taskCode = '<a href="/tasks/' + data.id + '" class="task-link" data-id="' +
                data.id + '">' + data.firstName + ' ' + data.secondName +'</a><br>';
            var buttonDelete = '<button class ="button-delete" data-id="' + data.id + '" type="submit" name="delete task">удалить задание</button>';
            $('#task-list')
                .append('<div class="inner-div">' + taskCode + buttonDelete + '</div>');
        };

    //Loading tasks on load page
    $.get('/tasks/', function(response)
    {
        for(i in response) {
            appendTask(response[i]);
        }
    });

    //Show change task form
    $('#show-put-task-form').click(function(){
        $('#put-task-form').css('display', 'flex');
    });

    //Closing change task form
    $('#put-task-form').click(function(event){
            if(event.target === this) {
                $(this).css('display', 'none');
            }
        });


    //Show adding task form
    $('#show-add-task-form').click(function(){
        $('#task-form').css('display', 'flex');
    });

    //Closing adding task form
    $('#task-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting task
    $(document).on('click', '#task-link', function(){
        var link = $(this);
        var bookId = link.data('id');

        $.ajax({
            method: "GET",
            cache:false,
            url: '/tasks/' + bookId,
            success: function(response)
            {
                var code = '<p> Описание задачи: ' + response.describeTask + '</p>' +
                    '<p> Выполнить к ' + response.deadlineDate + ' ' + response.deadlineTime + '</p>';
                link.parent().append(code);

            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Задача не найдена!');
                }
            }
        });
        return false;
    });

    //Adding task
    $('#save-task').click(function()
    {
        var data = $('#task-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/tasks/',
            data: data,
            success: function(response)
            {
                $('#task-form').css('display', 'none');
                var task = {};
                task.id = response;
                var dataArray = $('#task-form form').serializeArray();
                for(i in dataArray) {
                    task[dataArray[i]['name']] = dataArray[i]['value'];

                }
                appendTask(task);
                location.reload();
            }
        });
        return false;
    });

    $('#changeTask').click(function()
    {
            var data = $('#put-task-form form').serialize();
            $.ajax({
                method: "PUT",
                url: '/tasks/25',
                data: data,
                success: function(response)
                {
                    $('#put-task-form').css('display', 'none');
                    var task = {};
                    var dataArray = $('#put-task-form form').serializeArray();
                    for(i in dataArray) {
                        task[dataArray[i]['name']] = dataArray[i]['value'];
                    }
                    appendTask(task);
                    location.reload();
                }
            });
            return false;
        });

    $(document).on('click', '.button-delete', function(){
                var link = $(this);
                var taskId = link.data('id');
                var taskName = link.data('name')

                $.ajax({
                    method: "DELETE",
                    url: '/tasks/' + taskId,
                    success: function(response)
                    {
                        alert('Задание Удалено!');
                        location.reload();
                    },
                    error: function(response)
                    {
                        if(response.status == 404) {
                            alert('Задача не найдена!');
                        }
                    }
                });
                return false;
        });
});
