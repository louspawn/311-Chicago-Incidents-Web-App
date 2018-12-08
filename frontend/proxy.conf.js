
const PROXY_CONFIG = [
    {
        context: [
            "/users",
            "/requests",
            "/abandoned_vehicles_requests",
            "/garbage_carts_requests",
            "/graffiti_removal_requests",
            "/pot_holes_requests",
            "/rodent_baiting_requests",
            "/sanitation_code_complaints_requests",
            "/tree_debris_requests",
            "/tree_trims_requests"
        ],
        target: "http://localhost:8090",
        secure: false
    }
]
 
module.exports = PROXY_CONFIG;
