export enum Perfil{
    ADMIN = 1,
    USER = 2,
    VET= 3
}

export const PerfilLabel = {
    [Perfil.ADMIN]: 'Administrador',
    [Perfil.USER]: 'Usuario',
    [Perfil.VET]: 'Veterinario'
}