package ism.com;


import java.util.List;
import java.util.Scanner;

import ism.com.config.AppConfig;
import ism.com.entities.Client;
import ism.com.entities.Dette;
import ism.com.entities.Paiement;
import ism.com.entities.User;
import ism.com.entities.enums.CompteEtat;
import ism.com.entities.enums.DetteEtat;
import ism.com.entities.enums.PaiementEtat;
import ism.com.entities.enums.Role;
import ism.com.factory.RepositoryFactory;
import ism.com.repository.IRepository;
import ism.com.services.AuthService;
import ism.com.services.ClientService;
import ism.com.services.DetteService;
import ism.com.services.PaiementService;
import ism.com.services.UserService;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        //types
        String repositoryType = AppConfig.REPOSITORY_TYPE;
        // Initialisation des repositories et services
        IRepository<User> userRepo = (IRepository<User>) RepositoryFactory.createRepository(repositoryType, User.class);
        IRepository<Client> clientRepo = (IRepository<Client>) RepositoryFactory.createRepository(repositoryType, Client.class);
        IRepository<Dette> detteRepo = (IRepository<Dette>) RepositoryFactory.createRepository(repositoryType, Dette.class);
        IRepository<Paiement> paiementRepo = (IRepository<Paiement>) RepositoryFactory.createRepository(repositoryType, Paiement.class);


        
        AuthService authService = new AuthService(userRepo);
        UserService userService = new UserService(userRepo);
        ClientService clientService = new ClientService(clientRepo);
        DetteService detteService = new DetteService(detteRepo);
        PaiementService paiementService = new PaiementService(paiementRepo);

        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        System.out.println("=== Bienvenue dans l'application de gestion des dettes ===");

        // Gestion de la connexion
        while (currentUser == null) {
            System.out.println("\n--- Connexion ---");
            System.out.print("Login : ");
            String login = scanner.nextLine();
            System.out.print("Mot de passe : ");
            String password = scanner.nextLine();

            if (authService.login(login, password)) {
                currentUser = authService.getCurrentUser();
                System.out.println("Connexion réussie ! Bienvenue " + currentUser.getLogin());
            } else {
                System.out.println("Identifiants incorrects. Veuillez réessayer.");
            }
        }
        
        // afficher le menu correspondant au rôle
        boolean running = true;
        while (running) {
            System.out.println("\n=== Menu Principal ===");
            if (currentUser.getRole() == Role.ADMIN) {
                adminMenu(scanner, userService, clientService, detteService, paiementService,authService);
            } else if (currentUser.getRole() == Role.BOUTIQUIER) {
                boutiquierMenu(scanner, clientService, detteService, paiementService);
            } else if (currentUser.getRole() == Role.CLIENT) {
                clientMenu(scanner, detteService);
            } else {
                System.out.println("Rôle inconnu. Déconnexion forcée.");
                authService.logout();
                break;
            }
        }

        System.out.println("Au revoir !");
        scanner.close();
    }

    private static void adminMenu(Scanner scanner, UserService userService, ClientService clientService, DetteService detteService, PaiementService paiementService ,AuthService authService) {
        boolean managing = true;

        while (managing) {
            System.out.println("\n--- Menu Admin ---");
            System.out.println("1. Gérer les utilisateurs");
            System.out.println("2. Gérer les clients");
            System.out.println("3. Gérer les dettes");
            System.out.println("4. Gérer les paiements");
            System.out.println("5. Déconnexion");

            System.out.print("Votre choix : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> manageUtilisateurs(userService,scanner);
                case "2" -> manageClients(clientService, scanner);
                case "3" -> manageDettes(clientService, detteService, scanner);
                case "4" -> managePaiements(paiementService, scanner);
                case "5" -> {
                    authService.logout();
                    managing = false;
                    System.out.println("Déconnecté.");
                }
                default -> System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private static void boutiquierMenu(Scanner scanner, ClientService clientService, DetteService detteService, PaiementService paiementService) {
        boolean managing = true;

        while (managing) {
            System.out.println("\n--- Menu Boutiquier ---");
            System.out.println("1. Gérer les clients");
            System.out.println("2. Gérer les dettes");
            System.out.println("3. Gérer les paiements");
            System.out.println("4. Déconnexion");

            System.out.print("Votre choix : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> manageClients(clientService, scanner);
                case "2" -> manageDettes(clientService, detteService, scanner);
                case "3" -> managePaiements(paiementService, scanner);
                case "4" -> {
                    managing = false;
                    System.out.println("Déconnecté.");
                }
                default -> System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private static void clientMenu(Scanner scanner, DetteService detteService) {
        boolean managing = true;

        while (managing) {
            System.out.println("\n--- Menu Client ---");
            System.out.println("1. Lister mes dettes");
            System.out.println("2. Faire une demande de dette");
            System.out.println("3. Déconnexion");

            System.out.print("Votre choix : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> System.out.println("Lister les dettes non implémenté."); // Implémenter cette partie si nécessaire
                case "2" -> System.out.println("Demande de dette non implémentée."); // Implémenter cette partie si nécessaire
                case "3" -> managing = false;
                default -> System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    // Gestion des clients
    private static void manageClients(ClientService clientService, Scanner scanner) {
        boolean managingClients = true;
    
        while (managingClients) {
            System.out.println("\n--- Gestion des Clients ---");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Lister les clients");
            System.out.println("3. Rechercher un client par téléphone");
            System.out.println("4. Mettre à jour un client");
            System.out.println("5. Supprimer un client");
            System.out.println("6. Retour au menu principal");
    
            System.out.print("Votre choix : ");
            String choice = scanner.nextLine();
    
            switch (choice) {
                case "1" -> {
                    System.out.print("Nom : ");
                    String surname = scanner.nextLine();
                    System.out.print("Téléphone : ");
                    String phone = scanner.nextLine();
                    System.out.print("Adresse : ");
                    String address = scanner.nextLine();
    
                    try {
                        Client client = new Client();
                        client.setSurname(surname);
                        client.setPhone(phone);
                        client.setAddress(address);
                        clientService.createClient(client);
                        System.out.println("Client ajouté avec succès !");
                    } catch (Exception e) {
                        System.out.println("Erreur : " + e.getMessage());
                    }
                }
                case "2" -> {
                    List<Client> clients = clientService.findAllClients();
                    if (clients.isEmpty()) {
                        System.out.println("Aucun client trouvé.");
                    } else {
                        System.out.println("Liste des clients :");
                        for (Client client : clients) {
                            System.out.println(client.getId() + " - " + client.getSurname() + " - " + client.getPhone() + " - " + client.getAddress());
                        }
                    }
                }
                case "3" -> {
                    System.out.print("Téléphone du client : ");
                    String phone = scanner.nextLine();
                    Client client = clientService.findClientByPhone(phone);
                    if (client != null) {
                        System.out.println("Client trouvé : " + client.getSurname() + " - " + client.getPhone() + " - " + client.getAddress());
                    } else {
                        System.out.println("Aucun client trouvé avec ce téléphone.");
                    }
                }
                case "4" -> {
                    System.out.print("ID du client à mettre à jour : ");
                    int clientId = Integer.parseInt(scanner.nextLine());
                    Client client = clientService.findClientById(clientId);
                    if (client != null) {
                        System.out.print("Nouveau nom (" + client.getSurname() + ") : ");
                        String newSurname = scanner.nextLine();
                        System.out.print("Nouveau téléphone (" + client.getPhone() + ") : ");
                        String newPhone = scanner.nextLine();
                        System.out.print("Nouvelle adresse (" + client.getAddress() + ") : ");
                        String newAddress = scanner.nextLine();
    
                        if (!newSurname.isBlank()) client.setSurname(newSurname);
                        if (!newPhone.isBlank()) client.setPhone(newPhone);
                        if (!newAddress.isBlank()) client.setAddress(newAddress);
    
                        clientService.updateClient(client);
                        System.out.println("Client mis à jour !");
                    } else {
                        System.out.println("Client introuvable.");
                    }
                }
                case "5" -> {
                    System.out.print("ID du client à supprimer : ");
                    int clientId = Integer.parseInt(scanner.nextLine());
                    Client client = clientService.findClientById(clientId);
                    if (client != null) {
                        clientService.deleteClient(client);
                        System.out.println("Client supprimé !");
                    } else {
                        System.out.println("Client introuvable.");
                    }
                }
                case "6" -> managingClients = false;
                default -> System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }
    
    public static void manageUtilisateurs(UserService userService, Scanner scanner) {
        boolean managingUsers = true;

        while (managingUsers) {
            System.out.println("\n--- Gestion des Utilisateurs ---");
            System.out.println("1. Créer un compte utilisateur");
            System.out.println("2. Activer/Désactiver un compte utilisateur");
            System.out.println("3. Afficher les utilisateurs");
            System.out.println("4. Retour au menu principal");
    
            System.out.print("Votre choix : ");
            String choice = scanner.nextLine();
    
            switch (choice) {
                case "1" -> {
                    System.out.print("Login : ");
                    String login = scanner.nextLine();
                    System.out.print("Email : ");
                    String email = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String password = scanner.nextLine();
                    Role role = selectRole(scanner);
    
                    try {
                        User newUser = new User();
                        newUser.setLogin(login);
                        newUser.setEmail(email);
                        newUser.setPassword(password);
                        newUser.setRole(role);
                        newUser.setCompteEtat(CompteEtat.ACTIF);
                        userService.createAccountForClient(newUser);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erreur : " + e.getMessage());
                    }
                }
                case "2" -> {
                    List<User> users = userService.findAllUsers();
                    if (users.isEmpty()) {
                        System.out.println("Aucun utilisateur trouvé.");
                    } else {
                        System.out.println("Liste des utilisateurs :");
                        for (User user : users) {
                            System.out.println(user.getId() + " - " + user.getLogin() + " - " + user.getRole() + " - " + user.getCompteEtat());
                        }
                        System.out.print("ID de l'utilisateur à activer/désactiver : ");
                        int userId = Integer.parseInt(scanner.nextLine());
                        try {
                            userService.toggleAccountStatus(userId);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erreur : " + e.getMessage());
                        }
                    }
                }
                case "3" -> {
                    List<User> users = userService.findAllUsers();
                    if (users.isEmpty()) {
                        System.out.println("Aucun utilisateur trouvé.");
                    } else {
                        System.out.println("Liste des utilisateurs :");
                        for (User user : users) {
                            System.out.println(user.getId() + " - " + user.getLogin() + " - " + user.getRole() + " - " + user.getCompteEtat());
                        }
                    }
                }
                case "4" -> managingUsers = false;
                default -> System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private static Role selectRole(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Sélectionnez un rôle ---");
            System.out.println("1. ADMIN");
            System.out.println("2. BOUTIQUIER");
            System.out.println("3. CLIENT");
            System.out.print("Votre choix : ");
    
            String choice = scanner.nextLine();
    
            switch (choice) {
                case "1":
                    return Role.ADMIN;
                case "2":
                    return Role.BOUTIQUIER;
                case "3":
                    return Role.CLIENT;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

   private static void manageDettes(ClientService clientService, DetteService detteService, Scanner scanner) {
    boolean managingDettes = true;

    while (managingDettes) {
        System.out.println("\n--- Gestion des Dettes ---");
        System.out.println("1. Créer une nouvelle dette pour un client");
        System.out.println("2. Lister toutes les dettes");
        System.out.println("3. Rechercher une dette par ID");
        System.out.println("4. Lister les dettes non réglées");
        System.out.println("5. Valider ou annuler une dette");
        System.out.println("6. Retour au menu principal");

        System.out.print("Votre choix : ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> {
                System.out.print("ID du client : ");
                int clientId = Integer.parseInt(scanner.nextLine());
                Client client = clientService.findClientById(clientId);
                if (client != null) {
                    System.out.print("Montant de la dette : ");
                    double montant = Double.parseDouble(scanner.nextLine());
                    System.out.print("Montant versé : ");
                    double montantVerser = Double.parseDouble(scanner.nextLine());

                    Dette dette = new Dette();
                    dette.setClient(client);
                    dette.setMontant(montant);
                    dette.setMontantVerser(montantVerser);
                    dette.setMontantRestant(montant - montantVerser);
                    dette.setEtat(DetteEtat.EN_COURS);

                    detteService.createDette(client, dette);
                    System.out.println("Dette créée avec succès !");
                } else {
                    System.out.println("Client introuvable.");
                }
            }
            case "2" -> {
                List<Dette> dettes = detteService.findAllDettes();
                if (dettes.isEmpty()) {
                    System.out.println("Aucune dette trouvée.");
                } else {
                    System.out.println("Liste des dettes :");
                    for (Dette dette : dettes) {
                        System.out.println(dette.getId() + " - Client : " + dette.getClient().getSurname() +
                                " - Montant : " + dette.getMontant() +
                                " - Restant : " + dette.getMontantRestant() +
                                " - État : " + dette.getEtat());
                    }
                }
            }
            case "3" -> {
                System.out.print("ID de la dette : ");
                int detteId = Integer.parseInt(scanner.nextLine());
                Dette dette = detteService.findDetteById(detteId);
                if (dette != null) {
                    System.out.println("Dette trouvée :");
                    System.out.println("Client : " + dette.getClient().getSurname());
                    System.out.println("Montant : " + dette.getMontant());
                    System.out.println("Montant restant : " + dette.getMontantRestant());
                    System.out.println("État : " + dette.getEtat());
                } else {
                    System.out.println("Dette introuvable.");
                }
            }                                                                          
            case "4" -> {
                List<Dette> unpaidDettes = detteService.findUnpaidDettes();
                if (unpaidDettes.isEmpty()) {
                    System.out.println("Aucune dette non réglée trouvée.");
                } else {
                    System.out.println("Liste des dettes non réglées :");
                    for (Dette dette : unpaidDettes) {
                        System.out.println(dette.getId() + " - Client : " + dette.getClient().getSurname() +
                                " - Montant restant : " + dette.getMontantRestant());
                    }
                }
            }
            case "5" -> {
                System.out.print("ID de la dette à valider/annuler : ");
                int detteId = Integer.parseInt(scanner.nextLine());
                Dette dette = detteService.findDetteById(detteId);
                if (dette != null) {
                    System.out.println("1. Valider la dette");
                    System.out.println("2. Annuler la dette");
                    System.out.print("Votre choix : ");
                    String validationChoice = scanner.nextLine();
                    if (validationChoice.equals("1")) {
                        dette.setEtat(DetteEtat.SOLDEE);
                        detteService.updateDette(dette);
                        System.out.println("Dette validée avec succès.");
                    } else if (validationChoice.equals("2")) {
                        dette.setEtat(DetteEtat.ANNULEE);
                        detteService.updateDette(dette);
                        System.out.println("Dette annulée avec succès.");
                    } else {
                        System.out.println("Choix invalide.");
                    }
                } else {
                    System.out.println("Dette introuvable.");
                }
            }
            case "6" -> managingDettes = false;
            default -> System.out.println("Option invalide. Veuillez réessayer.");
        }
    }
}


   private static void managePaiements(PaiementService paiementService, Scanner scanner) {
    boolean managingPaiements = true;

    while (managingPaiements) {
        System.out.println("\n--- Gestion des Paiements ---");
        System.out.println("1. Enregistrer un nouveau paiement");
        System.out.println("2. Lister tous les paiements");
        System.out.println("3. Rechercher un paiement par ID");
        System.out.println("4. Retour au menu principal");

        System.out.print("Votre choix : ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> {
                System.out.print("ID de la dette associée : ");
                int detteId = Integer.parseInt(scanner.nextLine());
                System.out.print("Montant du paiement : ");
                double montant = Double.parseDouble(scanner.nextLine());

                Paiement paiement = new Paiement();
                paiement.setDetteId(detteId); // Associe le paiement à une dette
                paiement.setMontant(montant);
                paiement.setDate(new java.util.Date()); // Date actuelle
                paiement.setEtat(PaiementEtat.VALIDE); // Par défaut, le paiement est validé

                paiementService.createPaiement(paiement);
                System.out.println("Paiement enregistré avec succès !");
            }
            case "2" -> {
                List<Paiement> paiements = paiementService.findAllPaiements();
                if (paiements.isEmpty()) {
                    System.out.println("Aucun paiement trouvé.");
                } else {
                    System.out.println("Liste des paiements :");
                    for (Paiement paiement : paiements) {
                        System.out.println("ID : " + paiement.getId() +
                                " - Dette ID : " + paiement.getDetteId() +
                                " - Montant : " + paiement.getMontant() +
                                " - Date : " + paiement.getDate() +
                                " - État : " + paiement.getEtat());
                    }
                }
            }
            case "3" -> {
                System.out.print("ID du paiement à rechercher : ");
                int paiementId = Integer.parseInt(scanner.nextLine());
                Paiement paiement = paiementService.findPaiementById(paiementId);
                if (paiement != null) {
                    System.out.println("Paiement trouvé :");
                    System.out.println("ID : " + paiement.getId());
                    System.out.println("Dette ID : " + paiement.getDetteId());
                    System.out.println("Montant : " + paiement.getMontant());
                    System.out.println("Date : " + paiement.getDate());
                    System.out.println("État : " + paiement.getEtat());
                } else {
                    System.out.println("Paiement introuvable.");
                }
            }
            case "4" -> managingPaiements = false;
            default -> System.out.println("Option invalide. Veuillez réessayer.");
        }
    }
}

}
