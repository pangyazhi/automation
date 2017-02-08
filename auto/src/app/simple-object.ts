export class SimpleObject {
    name: String;
    description: String;
    createdBy: String;
    tags: String[];

    constructor(values: Object = {}) {
        Object.assign(this, values);

    }
}
