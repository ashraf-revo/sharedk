angular.module("shared").service("ChatService", function ($q, $timeout, AuthServerProvider) {
    var service = {}, ImagesListener = $q.defer(), NotificationsListener = $q.defer(), stomp = null;
    service.Images = function () {
        return ImagesListener.promise;
    };
    service.Notifications = function () {
        return NotificationsListener.promise;
    };

    service.send = function (to, message) {
        stomp.send(to, {}, message);
    };

    var reconnect = function () {
        $timeout(function () {
            initialize();
        }, 30000);
    };
    var startListener = function () {
        stomp.subscribe('/topic/images', function (data) {
            ImagesListener.notify(JSON.parse(data.body));

        });
        stomp.subscribe('/topic/notifications', function (data) {
            NotificationsListener.notify(JSON.parse(data.body));
        });
    };

    var initialize = function () {
        stomp = Stomp.over(new SockJS('/hello?access_token=' + AuthServerProvider.getToken()['access_token']));
        stomp.connect({}, startListener);
        stomp.onclose = reconnect;
    };

    initialize();
    return service;
});