export class Product {
    name: string;
    category: string;
    quantity: number;
    price: number;
    cart: boolean

    constructor(name ?: string, category ?: string, quantity?: number, price ?: number, cart ?: boolean){
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.cart = cart;
    }
}