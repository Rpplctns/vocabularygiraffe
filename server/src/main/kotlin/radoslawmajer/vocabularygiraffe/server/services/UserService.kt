package radoslawmajer.vocabularygiraffe.server.services

import org.springframework.stereotype.Service
import radoslawmajer.vocabularygiraffe.server.repository.UserRepository

@Service
class UserService (var userRepository: UserRepository) {

}