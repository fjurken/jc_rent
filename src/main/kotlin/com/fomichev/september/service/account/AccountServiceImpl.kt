package com.fomichev.september.service.account

// @Service
// class AccountServiceImpl(
//    private val securityService: SecurityService,
// ) : AccountService {
//
//    val log = KotlinLogging.logger {}

//    @Transactional
//    override fun getClientByEmail(email: String): Client {
//        return clientRepository.getClientByEmail(email) ?: throw UnknownEmailException(
//            "Client with email $email hasn't been registered!" +
//                "\nPlease, create new account.",
//            null
//        )
//    }

/**
 * Sign up
 */
//    @Transactional
//    override fun signUp(request: UserRequest): Client {
//        if (clientRepository.getClientByEmail(request.email) == null) {
//            val client = clientRepository.save(
//                Client(
//                    email = request.email,
//                    name = request.name!!
//                )
//            )
//            log.info("New client ${client.name} with id=${client.id} successfully signed up!")
//            clientBackRepository.save(
//                ClientBack(
//                    client_id = client.id!!,
//                    data = securityService.encryptPassword(request.password!!)
//                )
//            )
//            return client
//        } else throw EmailWasAlreadyRegisteredException(
//            "Client with email ${request.email} was already registered" +
//                "\nPlease, log in or click \"Forgot my password\"",
//            null
//        )
//    }

//    /**
//     * Log in
//     */
//    override fun logIn(request: UserRequest): Boolean {
//        val clientId = clientRepository.getClientByEmail(request.email)?.id
//            ?: throw UnknownEmailException(
//                "Client with email ${request.email} hasn't been registered!" +
//                    "\nPlease, create new account.",
//                null
//            )
//        val encryptedPass = clientBackRepository.getDataByClientId(clientId)
//        return encryptedPass == securityService.encryptPassword(request.password!!)
//    }

//    /**
//     * Restore password
//     */
//    override fun restorePassword(client: Client): Pair<String, String> {
//        val clientData = clientBackRepository.getByClientId(client.id!!)
//            ?: throw ClientBackDataIsEmptyException(
//                "ClientBack data for client $client is empty!", null
//            )
//        val pass = RandomString.make()
//        val encryptedPass = securityService.encryptPassword(pass)
//        clientData.data = encryptedPass
//        clientBackRepository.save(clientData)
//        return Pair(pass, encryptedPass)
//    }
// }
