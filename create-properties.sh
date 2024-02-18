#!/bin/bash

# File to store key-value pairs
destination="$1"
file="$destination/images.properties"


if [ ! -f "$file" ]; then
    touch "$file"
    echo "File $file created."
fi

# Predefined keys
keys=("Image" "Outputpath" "Header" "Subheader")  # Add your keys here

echo "Input values for keys: ${keys[@]}"


index=0

# Function to save key-value pairs to file
save_to_file() {
    local key_to_save="${keys[$1]}"
    local value_to_save="$2"

    # Check if the key exists in the file
    if grep -q "^$key_to_save=" "$file"; then
        # Replace existing value
        sed -i "s|^$key_to_save=.*|$key_to_save=$value_to_save|" "$file"
    else
        # Append key-value pair to file
        echo "$key_to_save=$value_to_save" >> "$file"
    fi
}
shift
# Loop through provided values and save them with predefined keys
for value; do
    save_to_file $index "$value"
    echo "Saved: ${keys[$index]}=$value"
    ((index++))
done

echo "Key-value pairs saved to $file."