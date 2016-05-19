package org.revo.Util

/**
 * Created by revo on 5/5/16.
 */
class View {
    interface User {}


    interface Image {}

    interface ImageUser {}

    interface ImageAndUser extends Image, ImageUser, User {}


    interface Notification {}

    interface NotificationUser {}

    interface NotificationAndUser extends Notification, NotificationUser, User {}

}
