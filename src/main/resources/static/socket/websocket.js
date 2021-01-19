'use strict';


var ws;

$(function () {
    connect();


});

var textChat = $('#text-mes');
var username1 = '';
var editInput = $('#chat-text');
var formSend = $('#formSend');
var btnSend =  $('#btnSend');
var textAreaUser = $('#textAreaUser');


function connect() {
    ws = new WebSocket('ws://localhost:8081/server1');
    ws.onopen = function (message) {
        onOpenRecerved(message);
    };
    ws.onmessage = function (message) {
        onMessageReceived(message);
    };
    ws.onclose = function (message) {
        processClose(message);
    };
    ws.onerror = function (message) {
        processError(message);
    };


    formSend.bind("enterKey",function(e){
        e.preventDefault();
        let txtInput = editInput.val().trim();
        let chatMessage = {
            username: username1,
            message: txtInput,
            type: 'CHAT'
        };

        ws.send(JSON.stringify(chatMessage));
        editInput.val("");

        console.log(JSON.stringify(chatMessage));
    });

    btnSend.click(function (e) {
        e.preventDefault();
        let txtInput = editInput.val().trim();
        let chatMessage = {
            username: username1,
            message: txtInput,
            type: 'CHAT'
        };

        ws.send(JSON.stringify(chatMessage));
        editInput.val("");

        console.log(JSON.stringify(chatMessage));
    });


}

function onOpenRecerved(payload) {
    // let message = JSON.parse(payload.data);
    // username1 = message.username;
    textChat.append("Server Connect... \n");

}



function onMessageReceived(payload) {
    let message ;
    try {

       message = JSON.parse(payload.data);
    } catch (e) {
        console.log(e);
        console.log('Error payload ' + payload);
        return;
    }

    console.log('message rececied : ' + JSON.stringify(message));
    console.log('message type : ' + message.type);
    switch (message.type) {
        case 'JOIN' :
            console.log('username ' + message.username);
            username1 = message.username;
            btnSend.attr("disabled", false);
            editInput.attr("disabled", false);
            break;
        case 'CLOSE' :
            console.log('CLose socket ' + message);
            break;
        case 'LIST_FRIENDS_ONLINE':
            let listUser = message.message.replace("list_user", "");
            textAreaUser.val(listUser) ;
            break;
        case 'AUTH_FAILED' :
            txtInput.attr("placeholder", "Auth Failed");
            break;
        case 'CHAT' :
            onChatReceived(message);
            break;
        default :
            console.log('Error cant understand type');
            break;
    }
}

function onChatReceived(message) {
    console.log('Username : ' + message.username);
    if (username1 != message.username) {
        textChat.append(`
    <div class="message-item">
        <div class="message-avatar">
        <figure class="avatar">
            <img src="./dist/media/img/man_avatar3.jpg" class="rounded-circle" alt="image">
        </figure>
        <div>
            <h5>${message.username}</h5>
            <div class="time">01:35 PM</div>
        </div>
        </div>
        <div class="message-content">
          ${message.message}
        </div>
    </div>`);
    } else {
        textChat.append(`
            <div class="message-item outgoing-message">
    <div class="message-avatar">
        <figure class="avatar">
            <img src="./dist/media/img/women_avatar5.jpg" class="rounded-circle" alt="image">
        </figure>
        <div>
            <h5>${message.username}</h5>
            <div class="time">01:20 PM <i class="ti-double-check text-info"></i></div>
        </div>
    </div>
    <div class="message-content">
        ${message.message}
    </div>
</div>
        `);
    }
}


function processClose(message) {
    textChat.append("Server Disconnect... \n");
}

function processError(message) {
    textChat.append("Error... " + message + " \n");
}



