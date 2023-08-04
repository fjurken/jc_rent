package com.fomichev.jc_rent.configuration

// @EnableWebSecurity
// class TokenAuthenticationFilter(
//    private val jwtTokenProvider: JwtTokenProvider
// ) : OncePerRequestFilter() {
//
//    override fun doFilterInternal(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        filterChain: FilterChain
//    ) {
//        val token = jwtTokenProvider.resolveToken(request)
//        if (token != null && jwtTokenProvider.validateToken(token)) {
//            SecurityContextHolder.getContext().authentication = jwtTokenProvider.getAuthentication(token)
//        }
//        filterChain.doFilter(request, response)
//    }
// }
