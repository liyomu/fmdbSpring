package websocket.handler;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {
	/** セッション一覧 */
	private ConcurrentHashMap<String, Set<WebSocketSession>> roomSessionPool = new ConcurrentHashMap<String, Set<WebSocketSession>>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String room = session.getUri().getQuery();
		System.out.println(room);
		roomSessionPool.compute(room, (key, sessions) -> {
			if (sessions == null) {
				sessions = new CopyOnWriteArraySet<>();
			}
			sessions.add(session);
			return sessions;

		});

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String room = session.getUri().getQuery();
		roomSessionPool.compute(room, (key, sessions) -> {
			sessions.remove(session);
			if (sessions.isEmpty()) {
				sessions = null;
			}
			return sessions;
		});
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String room = session.getUri().getQuery();
		System.out.println(message);
		System.out.println(message.getPayload());
		// 接続されているセッション（自分も含め）に転送する
		for (WebSocketSession roomSession : roomSessionPool.get(room)) {

			roomSession.sendMessage(message);
		}
	}

}