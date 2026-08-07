package com.voxchat.messenger.data.manager

object ServerConfig {
    const val DOMAIN = "voxchat.ru"
    
    // XMPP
    const val XMPP_HOST = DOMAIN
    const val XMPP_PORT = 5222
    const val WEBSOCKET_URL = "wss://$DOMAIN/ws/"
    const val BOSH_URL = "https://$DOMAIN/http-bind/"
    
    // HTTP API
    const val BASE_URL = "https://$DOMAIN/"
    const val UPLOAD_URL = "https://$DOMAIN/upload/"
    const val API_URL = "https://$DOMAIN/api/"
    const val PUSH_URL = "https://$DOMAIN/push/"
    
    // WebRTC (Coturn)
    const val STUN_URL = "stun:$DOMAIN:3478"
    const val TURN_URL = "turn:$DOMAIN:3478"
    const val TURNS_URL = "turns:$DOMAIN:5349"
    const val TURN_USERNAME = "turnuser"
    // TURN пароль получается динамически или из credentials.txt на сервере
    
    // Janus WebRTC Gateway
    const val JANUS_REST_URL = "https://$DOMAIN/janus/"
    const val JANUS_WS_URL = "wss://$DOMAIN/janus-ws/"
    
    // MinIO
    const val MINIO_URL = "https://$DOMAIN/minio/"
    
    // MUC (групповые чаты)
    const val MUC_DOMAIN = "conference.$DOMAIN"
    
    // API Key для Push-сервиса (должен храниться в secure storage)
    const val API_SECRET = "YOUR_API_SECRET_HERE" // Заменить на реальный ключ
}
