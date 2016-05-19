package org.revo.Model

import groovy.transform.Canonical

/**
 * Created by ashraf on 8/2/2015.
 */
@Canonical
class Message implements Serializable {
    String content
    String from
    String to
}