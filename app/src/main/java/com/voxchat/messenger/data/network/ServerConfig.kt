package com.voxchat.messenger.data.network

object ServerConfig {
    const val DOMAIN = "voxchat.ru"
    
    // XMPP Connection
    const val XMPP_HOST = DOMAIN
    const val XMPP_PORT = 5222
    const val XMPP_WEBSOCKET_URL = "wss://$DOMAIN/ws/"
    const val XMPP_BOSH_URL = "https://$DOMAIN/http-bind/"
    
    // HTTP API Endpoints
    const val BASE_URL = "https://$DOMAIN"
    const val UPLOAD_URL = "https://$DOMAIN/upload/"
    const val API_URL = "https://$DOMAIN/api/"
    const val PUSH_URL = "https://$DOMAIN/push/"
    const val MINIO_URL = "https://$DOMAIN/minio/"
    
    // WebRTC (Coturn)
    const val STUN_SERVER = "stun:$DOMAIN:3478"
    const val TURN_SERVER = "turn:$DOMAIN:3478"
    const val TURNS_SERVER = "turns:$DOMAIN:5349"
    const val TURN_USERNAME = "turnuser"
    // TURN password is retrieved from server dynamically
    
    // Janus WebRTC Gateway
    const val JANUS_REST_URL = "https://$DOMAIN/janus/"
    const val JANUS_WS_URL = "wss://$DOMAIN/janus-ws/"
    
    // MUC Domain
    const val MUC_DOMAIN = "conference.$DOMAIN"
    
    // Timeouts
    const val CONNECT_TIMEOUT = 30L
    const val READ_TIMEOUT = 60L
    const val WRITE_TIMEOUT = 60L
}
